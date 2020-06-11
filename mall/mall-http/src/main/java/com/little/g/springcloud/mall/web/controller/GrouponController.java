package com.little.g.springcloud.mall.web.controller;

import com.github.pagehelper.PageInfo;
import com.little.g.springcloud.common.ResultJson;
import com.little.g.springcloud.common.dto.Page;
import com.little.g.springcloud.common.utils.ResponseUtil;
import com.little.g.springcloud.common.web.annotation.LoginUser;
import com.little.g.springcloud.mall.api.*;
import com.little.g.springcloud.mall.dto.*;
import com.little.g.springcloud.mall.util.OrderUtil;
import com.little.g.springcloud.mall.validator.Order;
import com.little.g.springcloud.mall.validator.Sort;
import com.little.g.springcloud.mall.web.manager.GrouponRuleManager;
import com.little.g.springcloud.mall.web.manager.OrderManager;
import com.little.g.springcloud.mall.web.vo.*;
import com.little.g.springcloud.user.api.UserService;
import com.little.g.springcloud.user.dto.UserDTO;
import com.little.g.springcloud.user.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * 团购服务
 * <p>
 * 需要注意这里团购规则和团购活动的关系和区别。
 */
@Api("团购服务")
@RestController
@RequestMapping("/groupon")
@Validated
@Slf4j
public class GrouponController {

	@Reference
	private LitemallGrouponRulesService rulesService;

	@Autowired
	private GrouponRuleManager grouponRuleManager;

	@Reference
	private LitemallGrouponService grouponService;

	@Reference
	private LitemallGoodsService goodsService;

	@Reference
	private LitemallOrderService orderService;

	@Reference
	private LitemallOrderGoodsService orderGoodsService;

	@Reference
	private UserService userService;

	@Reference
	private ExpressService expressService;

	@Reference
	private LitemallGrouponRulesService grouponRulesService;

	@Resource
	private OrderManager orderManager;

	/**
	 * 团购规则列表
	 * @param page 分页页数
	 * @param limit 分页大小
	 * @return 团购规则列表
	 */
	@ApiOperation("团购规则列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "page", value = "分页", dataType = "int",
					required = false, defaultValue = "1", example = "1"),
			@ApiImplicitParam(name = "limit", value = "单页条数", dataType = "int",
					required = false, defaultValue = "10", example = "10") })
	@GetMapping("list")
	public ResultJson<Page<GrouponRuleVo>> list(
			@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer limit,
			@Sort @RequestParam(defaultValue = "add_time") String sort,
			@Order @RequestParam(defaultValue = "desc") String order) {
		PageInfo<GrouponRuleVo> grouponRuleVoList = grouponRuleManager.queryList(page,
				limit, sort, order);
		return ResponseUtil.okPage(grouponRuleVoList);
	}

	/**
	 * 团购活动详情
	 * @param userId 用户ID
	 * @param grouponId 团购活动ID
	 * @return 团购活动详情
	 */
	@ApiOperation("团购活动详情")
	@GetMapping("detail")
	@ApiImplicitParam(name = "grouponId", value = "团购ID", dataType = "int", example = "1")
	public ResultJson<GrouponDetailVo> detail(@LoginUser Integer userId,
			@RequestParam @NotNull Integer grouponId) {
		if (userId == null) {
			return ResponseUtil.unlogin();
		}

		LitemallGrouponDTO groupon = grouponService.queryById(userId, grouponId);
		if (groupon == null) {
			return ResponseUtil.badArgumentValue();
		}

		LitemallGrouponRulesDTO rules = rulesService.findById(groupon.getRulesId());
		if (rules == null) {
			return ResponseUtil.badArgumentValue();
		}

		ResultJson<OrderDetailVo> orderVoResult = orderManager.detail(userId,
				groupon.getOrderId());
		if (orderVoResult.getC() != null && orderVoResult.getC() > 0) {
			return ResponseUtil.fail(orderVoResult.getC(), orderVoResult.getM());
		}
		GrouponDetailVo detailVo = new GrouponDetailVo();
		if (orderVoResult.getD() != null) {
			detailVo.setOrderInfo(orderVoResult.getD().getOrderInfo());
			detailVo.setOrderGoods(orderVoResult.getD().getOrderGoods());
			detailVo.setExpressInfo(orderVoResult.getD().getExpressInfo());
		}

		UserVo creator = userService.findUserVoById(groupon.getCreatorUserId());
		List<UserVo> joiners = new ArrayList<>();
		joiners.add(creator);
		int linkGrouponId;
		// 这是一个团购发起记录
		if (groupon.getGrouponId() == 0) {
			linkGrouponId = groupon.getId();
		}
		else {
			linkGrouponId = groupon.getGrouponId();

		}
		List<LitemallGrouponDTO> groupons = grouponService.queryJoinRecord(linkGrouponId);

		UserVo joiner;
		for (LitemallGrouponDTO grouponItem : groupons) {
			joiner = userService.findUserVoById(grouponItem.getUserId());
			joiners.add(joiner);
		}

		detailVo.setLinkGrouponId(linkGrouponId);
		detailVo.setCreator(creator);
		detailVo.setJoiners(joiners);
		detailVo.setGroupon(groupon);
		detailVo.setRules(rules);
		return ResponseUtil.ok(detailVo);
	}

	/**
	 * 参加团购
	 * @param grouponId 团购活动ID
	 * @return 操作结果
	 */
	@ApiOperation("参加团购")
	@ApiImplicitParam(name = "grouponId", value = "团购活动ID", dataType = "int",
			example = "1")
	@GetMapping("join")
	public ResultJson<GrouponJoinVo> join(@RequestParam @NotNull Integer grouponId) {
		LitemallGrouponDTO groupon = grouponService.queryById(grouponId);
		if (groupon == null) {
			return ResponseUtil.badArgumentValue();
		}

		LitemallGrouponRulesDTO rules = rulesService.findById(groupon.getRulesId());
		if (rules == null) {
			return ResponseUtil.badArgumentValue();
		}

		LitemallGoodsDTO goods = goodsService.findById(rules.getGoodsId());
		if (goods == null) {
			return ResponseUtil.badArgumentValue();
		}

		GrouponJoinVo result = new GrouponJoinVo();
		result.setGroupon(groupon);
		result.setGoods(goods);
		return ResponseUtil.ok(result);
	}

	/**
	 * 用户开团或入团情况
	 * @param userId 用户ID
	 * @param showType 显示类型，如果是0，则是当前用户开的团购；否则，则是当前用户参加的团购
	 * @return 用户开团或入团情况
	 */
	@ApiOperation("用户开团或入团情况")
	@ApiImplicitParam(name = "showType", value = "显示类型，如果是0，则是当前用户开的团购；否则，则是当前用户参加的团购",
			dataType = "int", example = "1")
	@GetMapping("my")
	public ResultJson<MyGrouponResultVo> my(@LoginUser Integer userId,
			@RequestParam(defaultValue = "0") Integer showType) {
		if (userId == null) {
			return ResponseUtil.unlogin();
		}

		List<LitemallGrouponDTO> myGroupons;
		if (showType == 0) {
			myGroupons = grouponService.queryMyGroupon(userId);
		}
		else {
			myGroupons = grouponService.queryMyJoinGroupon(userId);
		}

		List<MyGrouponVo> grouponVoList = new ArrayList<>(myGroupons.size());

		LitemallOrderDTO order;
		LitemallGrouponRulesDTO rules;
		UserDTO creator;
		for (LitemallGrouponDTO groupon : myGroupons) {
			order = orderService.findById(userId, groupon.getOrderId());
			rules = rulesService.findById(groupon.getRulesId());
			creator = userService.getUserById(groupon.getCreatorUserId());

			MyGrouponVo grouponVo = new MyGrouponVo();
			// 填充团购信息
			grouponVo.setId(groupon.getId());
			grouponVo.setGroupon(groupon);
			grouponVo.setRules(rules);
			grouponVo.setCreator(creator.getName());

			int linkGrouponId;
			// 这是一个团购发起记录
			if (groupon.getGrouponId() == 0) {
				linkGrouponId = groupon.getId();
				grouponVo.setIsCreator(creator.getUid().equals(userId));
			}
			else {
				linkGrouponId = groupon.getGrouponId();
				grouponVo.setIsCreator(false);
			}
			int joinerCount = grouponService.countGroupon(linkGrouponId);
			grouponVo.setJoinerCount(joinerCount + 1);

			// 填充订单信息
			grouponVo.setOrderId(order.getId());
			grouponVo.setOrderSn(order.getOrderSn());
			grouponVo.setActualPrice(order.getActualPrice());
			grouponVo.setOrderStatusText(OrderUtil.orderStatusText(order));

			List<LitemallOrderGoodsDTO> orderGoodsList = orderGoodsService
					.queryByOid(order.getId());
			List<MyGrouponVo.MyGrouponOrderGoodsVo> orderGoodsVoList = new ArrayList<>(
					orderGoodsList.size());
			for (LitemallOrderGoodsDTO orderGoods : orderGoodsList) {
				MyGrouponVo.MyGrouponOrderGoodsVo orderGoodsVo = new MyGrouponVo.MyGrouponOrderGoodsVo();

				orderGoodsVo.setId(orderGoods.getId());
				orderGoodsVo.setGoodsName(orderGoods.getGoodsName());
				orderGoodsVo.setNumber(orderGoods.getNumber());
				orderGoodsVo.setPicUrl(orderGoods.getPicUrl());
				orderGoodsVoList.add(orderGoodsVo);
			}
			grouponVo.setGoodsList(orderGoodsVoList);
			grouponVoList.add(grouponVo);
		}

		MyGrouponResultVo result = new MyGrouponResultVo();
		result.setTotal(grouponVoList.size());
		result.setList(grouponVoList);

		return ResponseUtil.ok(result);
	}

}
