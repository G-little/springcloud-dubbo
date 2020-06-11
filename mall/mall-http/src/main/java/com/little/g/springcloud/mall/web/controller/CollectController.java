package com.little.g.springcloud.mall.web.controller;

import com.github.pagehelper.PageInfo;
import com.little.g.springcloud.common.ResultJson;
import com.little.g.springcloud.common.dto.Page;
import com.little.g.springcloud.common.utils.JacksonUtil;
import com.little.g.springcloud.common.utils.ResponseUtil;
import com.little.g.springcloud.common.web.annotation.LoginUser;
import com.little.g.springcloud.mall.api.LitemallCollectService;
import com.little.g.springcloud.mall.api.LitemallGoodsService;
import com.little.g.springcloud.mall.dto.LitemallCollectDTO;
import com.little.g.springcloud.mall.dto.LitemallGoodsDTO;
import com.little.g.springcloud.mall.validator.Order;
import com.little.g.springcloud.mall.validator.Sort;
import com.little.g.springcloud.mall.web.vo.CollectListVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户收藏服务
 */
@Api("用户收藏")
@RestController
@RequestMapping("/collect")
@Validated
@Slf4j
public class CollectController {

	@Reference
	private LitemallCollectService collectService;

	@Reference
	private LitemallGoodsService goodsService;

	/**
	 * 用户收藏列表
	 * @param userId 用户ID
	 * @param type 类型，如果是0则是商品收藏，如果是1则是专题收藏
	 * @param page 分页页数
	 * @param limit 分页大小
	 * @return 用户收藏列表
	 */
	@ApiOperation(value = "收藏列表", notes = "用户收藏列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "type", value = "类型，如果是0则是商品收藏，如果是1则是专题收藏",
					allowableValues = "0,1", dataType = "Byte", required = true),
			@ApiImplicitParam(name = "page", value = "分页", dataType = "int",
					example = "1", required = false, defaultValue = "1"),
			@ApiImplicitParam(name = "limit", value = "单页条数", dataType = "int",
					required = false, defaultValue = "10", example = "10") })
	@GetMapping("list")
	public ResultJson<Page<CollectListVo>> list(@LoginUser Integer userId,
			@RequestParam @NotNull Byte type,
			@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer limit,
			@Sort @RequestParam(defaultValue = "add_time") String sort,
			@Order @RequestParam(defaultValue = "desc") String order) {
		if (userId == null) {
			return ResponseUtil.unlogin();
		}

		PageInfo<LitemallCollectDTO> pageInfo = collectService.queryByType(userId, type,
				page, limit, sort, order);
		if (pageInfo == null) {
			return ResponseUtil.ok();
		}
		List<LitemallCollectDTO> collectList = pageInfo.getList();

		List<CollectListVo> collects = new ArrayList<>(collectList.size());
		for (LitemallCollectDTO collect : collectList) {
			CollectListVo vo = new CollectListVo();
			vo.setId(collect.getId());
			vo.setType(collect.getType());
			vo.setValueId(collect.getValueId());
			LitemallGoodsDTO goods = goodsService.findById(collect.getValueId());
			vo.setName(goods.getName());
			vo.setBrief(goods.getBrief());
			vo.setPicUrl(goods.getPicUrl());
			vo.setRetailPrice(goods.getRetailPrice());

			collects.add(vo);
		}

		return ResponseUtil.okList(collects, pageInfo);
	}

	/**
	 * 用户收藏添加或删除
	 * <p>
	 * 如果商品没有收藏，则添加收藏；如果商品已经收藏，则删除收藏状态。
	 * @param userId 用户ID
	 * @param body 请求内容，{ type: xxx, valueId: xxx }
	 * @return 操作结果
	 */

	@ApiOperation(value = "用户收藏添加或删除", notes = "如果商品没有收藏，则添加收藏；如果商品已经收藏，则删除收藏状态。")
	@ApiImplicitParam(value = "收藏类型，收藏值， { type: xxx, valueId: xxx }")
	@PostMapping("addordelete")
	public ResultJson addordelete(@LoginUser Integer userId, @RequestBody String body) {
		if (userId == null) {
			return ResponseUtil.unlogin();
		}

		Byte type = JacksonUtil.parseByte(body, "type");
		Integer valueId = JacksonUtil.parseInteger(body, "valueId");
		if (!ObjectUtils.allNotNull(type, valueId)) {
			return ResponseUtil.badArgument();
		}

		LitemallCollectDTO collect = collectService.queryByTypeAndValue(userId, type,
				valueId);

		if (collect != null) {
			collectService.deleteById(collect.getId());
		}
		else {
			collect = new LitemallCollectDTO();
			collect.setUserId(userId);
			collect.setValueId(valueId);
			collect.setType(type);
			collectService.add(collect);
		}

		return ResponseUtil.ok();
	}

}
