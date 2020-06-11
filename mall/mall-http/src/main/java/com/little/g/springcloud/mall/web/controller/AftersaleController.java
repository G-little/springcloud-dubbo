package com.little.g.springcloud.mall.web.controller;

import com.github.pagehelper.PageInfo;
import com.little.g.springcloud.common.ResultJson;
import com.little.g.springcloud.common.dto.Page;
import com.little.g.springcloud.common.utils.ResponseUtil;
import com.little.g.springcloud.common.web.annotation.LoginUser;
import com.little.g.springcloud.mall.MallErrorCodes;
import com.little.g.springcloud.mall.api.LitemallAftersaleService;
import com.little.g.springcloud.mall.api.LitemallOrderGoodsService;
import com.little.g.springcloud.mall.api.LitemallOrderService;
import com.little.g.springcloud.mall.dto.LitemallAftersaleDTO;
import com.little.g.springcloud.mall.dto.LitemallOrderDTO;
import com.little.g.springcloud.mall.dto.LitemallOrderGoodsDTO;
import com.little.g.springcloud.mall.util.AftersaleConstant;
import com.little.g.springcloud.mall.util.OrderUtil;
import com.little.g.springcloud.mall.validator.Order;
import com.little.g.springcloud.mall.validator.Sort;
import com.little.g.springcloud.mall.web.vo.AftersaleDetailVo;
import com.little.g.springcloud.mall.web.vo.AftersaleVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 售后服务
 * <p>
 * 目前只支持订单整体售后，不支持订单商品单个售后
 * <p>
 * 一个订单只能有一个售后记录
 */
@Api("收货服务")
@RestController
@RequestMapping("/aftersale")
@Validated
@Slf4j
public class AftersaleController {

	@Reference
	private LitemallAftersaleService aftersaleService;

	@Reference
	private LitemallOrderService orderService;

	@Reference
	private LitemallOrderGoodsService orderGoodsService;

	/**
	 * 售后列表
	 * @param userId 用户ID
	 * @param status 状态类型，如果是空则是全部
	 * @param page 分页页数
	 * @param limit 分页大小
	 * @param sort 排序字段
	 * @param order 排序方式
	 * @return 售后列表
	 */
	@ApiOperation(value = "售后列表", notes = "根据状态时间查询售后列表")
	@ApiImplicitParams({ @ApiImplicitParam(name = "status",
			value = "状态类型，如果是空则是全部 0是可申请，1是用户已申请，2是管理员审核通过，3是管理员退款成功，4是管理员审核拒绝，5是用户已取消",
			defaultValue = "0", allowableValues = "0,1,2,3,4,5", required = true,
			dataType = "byte", example = "0"),
			@ApiImplicitParam(name = "page", value = "分页", defaultValue = "1",
					dataType = "int", example = "1"),
			@ApiImplicitParam(name = "limit", value = "单页条数", defaultValue = "10",
					dataType = "int", example = "10") })
	@GetMapping("list")
	public ResultJson<Page<AftersaleVo>> list(@LoginUser Integer userId,
			@RequestParam Short status, @RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer limit,
			@Sort @RequestParam(defaultValue = "add_time") String sort,
			@Order @RequestParam(defaultValue = "desc") String order) {
		if (userId == null) {
			return ResponseUtil.unlogin();
		}

		PageInfo<LitemallAftersaleDTO> aftersalePage = aftersaleService.queryList(userId,
				status, page, limit, sort, order);

		if (aftersalePage == null) {
			return ResponseUtil.ok();
		}

		List<LitemallAftersaleDTO> aftersaleList = aftersalePage.getList();
		List<AftersaleVo> aftersaleVoList = null;
		if (aftersaleList != null) {
			aftersaleVoList = new ArrayList<>(aftersaleList.size());
			for (LitemallAftersaleDTO aftersale : aftersaleList) {
				List<LitemallOrderGoodsDTO> orderGoodsList = orderGoodsService
						.queryByOid(aftersale.getOrderId());
				AftersaleVo vo = new AftersaleVo();
				vo.setAftersale(aftersale);
				vo.setGoodsList(orderGoodsList);

				aftersaleVoList.add(vo);
			}
		}

		return ResponseUtil.okList(aftersaleVoList, aftersalePage);
	}

	/**
	 * 售后详情
	 * @param orderId 订单ID
	 * @return 售后详情
	 */
	@ApiOperation(value = "售后详情", notes = "售后详情")
	@ApiImplicitParams({ @ApiImplicitParam(name = "orderId", value = "订单ID",
			dataType = "int", required = true, example = "1") })
	@GetMapping("detail")
	public ResultJson<AftersaleDetailVo> detail(@LoginUser Integer userId,
			@RequestParam @NotNull Integer orderId) {
		if (userId == null) {
			return ResponseUtil.unlogin();
		}

		LitemallOrderDTO order = orderService.findById(userId, orderId);
		if (order == null) {
			return ResponseUtil.badArgumentValue();
		}
		List<LitemallOrderGoodsDTO> orderGoodsList = orderGoodsService
				.queryByOid(orderId);
		LitemallAftersaleDTO aftersale = aftersaleService.findByOrderId(userId, orderId);

		AftersaleDetailVo vo = new AftersaleDetailVo();
		vo.setAftersale(aftersale);
		vo.setOrder(order);
		vo.setGoodsList(orderGoodsList);
		return ResponseUtil.ok(vo);
	}

	/**
	 * 申请售后
	 * @param userId 用户ID
	 * @param aftersale 用户售后信息
	 * @return 操作结果
	 */
	@ApiOperation(value = "申请收货", notes = "申请售后")
	@PostMapping("submit")
	public ResultJson submit(@LoginUser Integer userId,
			@RequestBody LitemallAftersaleDTO aftersale) {
		if (userId == null) {
			return ResponseUtil.unlogin();
		}
		ResultJson error = validate(aftersale);
		if (error != null) {
			return error;
		}
		// 进一步验证
		Integer orderId = aftersale.getOrderId();
		if (orderId == null) {
			return ResponseUtil.badArgument();
		}
		LitemallOrderDTO order = orderService.findById(userId, orderId);
		if (order == null) {
			return ResponseUtil.badArgumentValue();
		}

		// 订单必须完成才能进入售后流程。
		if (!OrderUtil.isConfirmStatus(order) && !OrderUtil.isAutoConfirmStatus(order)) {
			return ResponseUtil.fail(MallErrorCodes.AFTERSALE_UNALLOWED, "不能申请售后");
		}
		BigDecimal amount = order.getActualPrice().subtract(order.getFreightPrice());
		if (aftersale.getAmount().compareTo(amount) > 0) {
			return ResponseUtil.fail(MallErrorCodes.AFTERSALE_INVALID_AMOUNT, "退款金额不正确");
		}
		Short afterStatus = order.getAftersaleStatus();
		if (afterStatus.equals(AftersaleConstant.STATUS_RECEPT)
				|| afterStatus.equals(AftersaleConstant.STATUS_REFUND)) {
			return ResponseUtil.fail(MallErrorCodes.AFTERSALE_INVALID_AMOUNT, "已申请售后");
		}

		// 如果有旧的售后记录则删除（例如用户已取消，管理员拒绝）
		aftersaleService.deleteByOrderId(userId, orderId);

		aftersale.setStatus(AftersaleConstant.STATUS_REQUEST);
		aftersale.setAftersaleSn(aftersaleService.generateAftersaleSn(userId));
		aftersale.setUserId(userId);
		aftersaleService.add(aftersale);

		// 订单的aftersale_status和售后记录的status是一致的。
		orderService.updateAftersaleStatus(orderId, AftersaleConstant.STATUS_REQUEST);
		return ResponseUtil.ok();
	}

	/**
	 * 取消售后
	 * <p>
	 * 如果管理员还没有审核，用户可以取消自己的售后申请
	 * @param userId 用户ID
	 * @param id 售后 订单号
	 * @return 操作结果
	 */
	@ApiOperation(value = "取消售后", notes = "取消售后")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "售后单ID", dataType = "int",
			required = true, example = "1") })
	@PostMapping("cancel")
	public ResultJson cancel(@LoginUser Integer userId, @RequestParam Integer id) {
		if (userId == null) {
			return ResponseUtil.unlogin();
		}

		LitemallAftersaleDTO aftersaleOne = aftersaleService.findById(userId, id);
		if (aftersaleOne == null) {
			return ResponseUtil.badArgument();
		}

		Integer orderId = aftersaleOne.getOrderId();
		LitemallOrderDTO order = orderService.findById(userId, orderId);
		if (!order.getUserId().equals(userId)) {
			return ResponseUtil.badArgumentValue();
		}

		// 订单必须完成才能进入售后流程。
		if (!OrderUtil.isConfirmStatus(order) && !OrderUtil.isAutoConfirmStatus(order)) {
			return ResponseUtil.fail(MallErrorCodes.AFTERSALE_UNALLOWED, "不支持售后");
		}
		Short afterStatus = order.getAftersaleStatus();
		if (!afterStatus.equals(AftersaleConstant.STATUS_REQUEST)) {
			return ResponseUtil.fail(MallErrorCodes.AFTERSALE_INVALID_STATUS, "不能取消售后");
		}

		LitemallAftersaleDTO aftersale = new LitemallAftersaleDTO();
		aftersale.setId(id);
		aftersale.setStatus(AftersaleConstant.STATUS_CANCEL);
		aftersale.setUserId(userId);
		aftersaleService.updateById(aftersale);

		// 订单的aftersale_status和售后记录的status是一致的。
		orderService.updateAftersaleStatus(orderId, AftersaleConstant.STATUS_CANCEL);
		return ResponseUtil.ok();
	}

	private ResultJson validate(LitemallAftersaleDTO aftersale) {
		Short type = aftersale.getType();
		if (type == null) {
			return ResponseUtil.badArgument();
		}
		BigDecimal amount = aftersale.getAmount();
		if (amount == null) {
			return ResponseUtil.badArgument();
		}
		String reason = aftersale.getReason();
		if (StringUtils.isEmpty(reason)) {
			return ResponseUtil.badArgument();
		}
		return null;
	}

}
