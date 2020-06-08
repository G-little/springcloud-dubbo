package com.little.g.springcloud.mall.web.controller;

import com.github.pagehelper.PageInfo;
import com.little.g.springcloud.common.ResultJson;
import com.little.g.springcloud.common.dto.Page;
import com.little.g.springcloud.common.utils.JacksonUtil;
import com.little.g.springcloud.common.utils.ResponseUtil;
import com.little.g.springcloud.common.web.annotation.LoginUser;
import com.little.g.springcloud.mall.MallErrorCodes;
import com.little.g.springcloud.mall.api.*;
import com.little.g.springcloud.mall.dto.LitemallCartDTO;
import com.little.g.springcloud.mall.dto.LitemallCouponDTO;
import com.little.g.springcloud.mall.dto.LitemallCouponUserDTO;
import com.little.g.springcloud.mall.dto.LitemallGrouponRulesDTO;
import com.little.g.springcloud.mall.util.CouponConstant;
import com.little.g.springcloud.mall.validator.Order;
import com.little.g.springcloud.mall.validator.Sort;
import com.little.g.springcloud.mall.web.vo.CouponVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 优惠券服务
 */
@Api("优惠券服务")
@RestController
@RequestMapping("/coupon")
@Validated
@Slf4j
public class CouponController {

	@Reference
	private LitemallCouponService couponService;

	@Reference
	private LitemallCouponUserService couponUserService;

	@Reference
	private LitemallGrouponRulesService grouponRulesService;

	@Reference
	private LitemallCartService cartService;

	@Reference
	private CouponVerifyService couponVerifyService;

	/**
	 * 优惠券列表
	 * @param page
	 * @param limit
	 * @param sort
	 * @param order
	 * @return
	 */
	@ApiOperation("优惠券列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "page", value = "分页", dataType = "Integer",
					required = false, defaultValue = "1"),
			@ApiImplicitParam(name = "limit", value = "单页条数", dataType = "Integer",
					required = false, defaultValue = "10") })
	@GetMapping("list")
	public ResultJson<Page<LitemallCouponDTO>> list(
			@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer limit,
			@Sort @RequestParam(defaultValue = "add_time") String sort,
			@Order @RequestParam(defaultValue = "desc") String order) {

		List<LitemallCouponDTO> couponList = couponService.queryList(page, limit, sort,
				order);
		return ResponseUtil.okList(couponList);
	}

	/**
	 * 个人优惠券列表
	 * @param userId
	 * @param status
	 * @param page
	 * @param limit
	 * @param sort
	 * @param order
	 * @return
	 */
	@ApiOperation("个人优惠券列表")
	@GetMapping("mylist")
	public ResultJson<Page<CouponVo>> mylist(@LoginUser Integer userId, Short status,
			@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer limit,
			@Sort @RequestParam(defaultValue = "add_time") String sort,
			@Order @RequestParam(defaultValue = "desc") String order) {
		if (userId == null) {
			return ResponseUtil.unlogin();
		}

		PageInfo<LitemallCouponUserDTO> pageInfo = couponUserService.queryList(userId,
				null, status, page, limit, sort, order);
		if (pageInfo == null) {
			return ResponseUtil.ok();
		}
		List<CouponVo> couponVoList = change(pageInfo.getList());
		return ResponseUtil.okList(couponVoList, pageInfo);
	}

	private List<CouponVo> change(List<LitemallCouponUserDTO> couponList) {
		List<CouponVo> couponVoList = new ArrayList<>(couponList.size());
		for (LitemallCouponUserDTO couponUser : couponList) {
			Integer couponId = couponUser.getCouponId();
			LitemallCouponDTO coupon = couponService.findById(couponId);
			CouponVo couponVo = new CouponVo();
			couponVo.setId(couponUser.getId());
			couponVo.setCid(coupon.getId());
			couponVo.setName(coupon.getName());
			couponVo.setDesc(coupon.getDesc());
			couponVo.setTag(coupon.getTag());
			couponVo.setMin(coupon.getMin());
			couponVo.setDiscount(coupon.getDiscount());
			couponVo.setStartTime(couponUser.getStartTime());
			couponVo.setEndTime(couponUser.getEndTime());

			couponVoList.add(couponVo);
		}

		return couponVoList;
	}

	/**
	 * 当前购物车下单商品订单可用优惠券
	 * @param userId
	 * @param cartId
	 * @param grouponRulesId
	 * @return
	 */
	@ApiOperation("当前购物车下单商品订单可用优惠券")
	@GetMapping("selectlist")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "cartId", value = "购物车ID", required = false),
			@ApiImplicitParam(name = "grouponRulesId", value = "团购规则ID",
					required = false) })
	public ResultJson<Page<CouponVo>> selectlist(@LoginUser Integer userId,
			Integer cartId, Integer grouponRulesId) {
		if (userId == null) {
			return ResponseUtil.unlogin();
		}

		// 团购优惠
		BigDecimal grouponPrice = new BigDecimal(0.00);
		LitemallGrouponRulesDTO grouponRules = grouponRulesService
				.findById(grouponRulesId);
		if (grouponRules != null) {
			grouponPrice = grouponRules.getDiscount();
		}

		// 商品价格
		List<LitemallCartDTO> checkedGoodsList = null;
		if (cartId == null || cartId.equals(0)) {
			checkedGoodsList = cartService.queryByUidAndChecked(userId);
		}
		else {
			LitemallCartDTO cart = cartService.findById(userId, cartId);
			if (cart == null) {
				return ResponseUtil.badArgumentValue();
			}
			checkedGoodsList = new ArrayList<>(1);
			checkedGoodsList.add(cart);
		}
		BigDecimal checkedGoodsPrice = new BigDecimal(0.00);
		for (LitemallCartDTO cart : checkedGoodsList) {
			// 只有当团购规格商品ID符合才进行团购优惠
			if (grouponRules != null
					&& grouponRules.getGoodsId().equals(cart.getGoodsId())) {
				checkedGoodsPrice = checkedGoodsPrice
						.add(cart.getPrice().subtract(grouponPrice)
								.multiply(new BigDecimal(cart.getNumber())));
			}
			else {
				checkedGoodsPrice = checkedGoodsPrice
						.add(cart.getPrice().multiply(new BigDecimal(cart.getNumber())));
			}
		}

		// 计算优惠券可用情况
		List<LitemallCouponUserDTO> couponUserList = couponUserService.queryAll(userId);
		List<CouponVo> couponVoList = change(couponUserList);
		for (CouponVo cv : couponVoList) {
			LitemallCouponDTO coupon = couponVerifyService.checkCoupon(userId,
					cv.getCid(), cv.getId(), checkedGoodsPrice);
			cv.setAvailable(coupon != null);
		}

		return ResponseUtil.okList(couponVoList);
	}

	/**
	 * 优惠券领取
	 * @param userId 用户ID
	 * @param body 请求内容， { couponId: xxx }
	 * @return 操作结果
	 */

	@ApiOperation("优惠券领取")
	@ApiImplicitParam(" 请求内容， { couponId: xxx }")
	@PostMapping("receive")
	public ResultJson receive(@LoginUser Integer userId, @RequestBody String body) {
		if (userId == null) {
			return ResponseUtil.unlogin();
		}

		Integer couponId = JacksonUtil.parseInteger(body, "couponId");
		if (couponId == null) {
			return ResponseUtil.badArgument();
		}

		LitemallCouponDTO coupon = couponService.findById(couponId);
		if (coupon == null) {
			return ResponseUtil.badArgumentValue();
		}

		// 当前已领取数量和总数量比较
		Integer total = coupon.getTotal();
		Integer totalCoupons = couponUserService.countCoupon(couponId);
		if ((total != 0) && (totalCoupons >= total)) {
			return ResponseUtil.fail(MallErrorCodes.COUPON_EXCEED_LIMIT, "优惠券已领完");
		}

		// 当前用户已领取数量和用户限领数量比较
		Integer limit = coupon.getLimit().intValue();
		Integer userCounpons = couponUserService.countUserAndCoupon(userId, couponId);
		if ((limit != 0) && (userCounpons >= limit)) {
			return ResponseUtil.fail(MallErrorCodes.COUPON_EXCEED_LIMIT, "优惠券已经领取过");
		}

		// 优惠券分发类型
		// 例如注册赠券类型的优惠券不能领取
		Short type = coupon.getType();
		if (type.equals(CouponConstant.TYPE_REGISTER)) {
			return ResponseUtil.fail(MallErrorCodes.COUPON_RECEIVE_FAIL, "新用户优惠券自动发送");
		}
		else if (type.equals(CouponConstant.TYPE_CODE)) {
			return ResponseUtil.fail(MallErrorCodes.COUPON_RECEIVE_FAIL, "优惠券只能兑换");
		}
		else if (!type.equals(CouponConstant.TYPE_COMMON)) {
			return ResponseUtil.fail(MallErrorCodes.COUPON_RECEIVE_FAIL, "优惠券类型不支持");
		}

		// 优惠券状态，已下架或者过期不能领取
		Short status = coupon.getStatus();
		if (status.equals(CouponConstant.STATUS_OUT)) {
			return ResponseUtil.fail(MallErrorCodes.COUPON_EXCEED_LIMIT, "优惠券已领完");
		}
		else if (status.equals(CouponConstant.STATUS_EXPIRED)) {
			return ResponseUtil.fail(MallErrorCodes.COUPON_RECEIVE_FAIL, "优惠券已经过期");
		}

		// 用户领券记录
		LitemallCouponUserDTO couponUser = new LitemallCouponUserDTO();
		couponUser.setCouponId(couponId);
		couponUser.setUserId(userId);
		Short timeType = coupon.getTimeType();
		if (timeType.equals(CouponConstant.TIME_TYPE_TIME)) {
			couponUser.setStartTime(coupon.getStartTime());
			couponUser.setEndTime(coupon.getEndTime());
		}
		else {
			LocalDateTime now = LocalDateTime.now();
			couponUser.setStartTime(now);
			couponUser.setEndTime(now.plusDays(coupon.getDays()));
		}
		couponUserService.add(couponUser);

		return ResponseUtil.ok();
	}

	/**
	 * 优惠券兑换
	 * @param userId 用户ID
	 * @param body 请求内容， { code: xxx }
	 * @return 操作结果
	 */
	@ApiOperation("优惠券兑换")
	@ApiImplicitParam("请求内容， { code: xxx }")
	@PostMapping("exchange")
	public ResultJson exchange(@LoginUser Integer userId, @RequestBody String body) {
		if (userId == null) {
			return ResponseUtil.unlogin();
		}

		String code = JacksonUtil.parseString(body, "code");
		if (code == null) {
			return ResponseUtil.badArgument();
		}

		LitemallCouponDTO coupon = couponService.findByCode(code);
		if (coupon == null) {
			return ResponseUtil.fail(MallErrorCodes.COUPON_CODE_INVALID, "优惠券不正确");
		}
		Integer couponId = coupon.getId();

		// 当前已领取数量和总数量比较
		Integer total = coupon.getTotal();
		Integer totalCoupons = couponUserService.countCoupon(couponId);
		if ((total != 0) && (totalCoupons >= total)) {
			return ResponseUtil.fail(MallErrorCodes.COUPON_EXCEED_LIMIT, "优惠券已兑换");
		}

		// 当前用户已领取数量和用户限领数量比较
		Integer limit = coupon.getLimit().intValue();
		Integer userCounpons = couponUserService.countUserAndCoupon(userId, couponId);
		if ((limit != 0) && (userCounpons >= limit)) {
			return ResponseUtil.fail(MallErrorCodes.COUPON_EXCEED_LIMIT, "优惠券已兑换");
		}

		// 优惠券分发类型
		// 例如注册赠券类型的优惠券不能领取
		Short type = coupon.getType();
		if (type.equals(CouponConstant.TYPE_REGISTER)) {
			return ResponseUtil.fail(MallErrorCodes.COUPON_RECEIVE_FAIL, "新用户优惠券自动发送");
		}
		else if (type.equals(CouponConstant.TYPE_COMMON)) {
			return ResponseUtil.fail(MallErrorCodes.COUPON_RECEIVE_FAIL, "优惠券只能领取，不能兑换");
		}
		else if (!type.equals(CouponConstant.TYPE_CODE)) {
			return ResponseUtil.fail(MallErrorCodes.COUPON_RECEIVE_FAIL, "优惠券类型不支持");
		}

		// 优惠券状态，已下架或者过期不能领取
		Short status = coupon.getStatus();
		if (status.equals(CouponConstant.STATUS_OUT)) {
			return ResponseUtil.fail(MallErrorCodes.COUPON_EXCEED_LIMIT, "优惠券已兑换");
		}
		else if (status.equals(CouponConstant.STATUS_EXPIRED)) {
			return ResponseUtil.fail(MallErrorCodes.COUPON_RECEIVE_FAIL, "优惠券已经过期");
		}

		// 用户领券记录
		LitemallCouponUserDTO couponUser = new LitemallCouponUserDTO();
		couponUser.setCouponId(couponId);
		couponUser.setUserId(userId);
		Short timeType = coupon.getTimeType();
		if (timeType.equals(CouponConstant.TIME_TYPE_TIME)) {
			couponUser.setStartTime(coupon.getStartTime());
			couponUser.setEndTime(coupon.getEndTime());
		}
		else {
			LocalDateTime now = LocalDateTime.now();
			couponUser.setStartTime(now);
			couponUser.setEndTime(now.plusDays(coupon.getDays()));
		}
		couponUserService.add(couponUser);

		return ResponseUtil.ok();
	}

}
