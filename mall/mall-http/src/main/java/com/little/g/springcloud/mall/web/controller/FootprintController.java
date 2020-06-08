package com.little.g.springcloud.mall.web.controller;

import com.github.pagehelper.PageInfo;
import com.little.g.springcloud.common.ResultJson;
import com.little.g.springcloud.common.dto.Page;
import com.little.g.springcloud.common.utils.JacksonUtil;
import com.little.g.springcloud.common.utils.ResponseUtil;
import com.little.g.springcloud.common.web.annotation.LoginUser;
import com.little.g.springcloud.mall.api.LitemallFootprintService;
import com.little.g.springcloud.mall.api.LitemallGoodsService;
import com.little.g.springcloud.mall.dto.LitemallFootprintDTO;
import com.little.g.springcloud.mall.dto.LitemallGoodsDTO;
import com.little.g.springcloud.mall.web.vo.FootprintVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户访问足迹服务
 */
@Api("用户访问足迹服务")
@RestController
@RequestMapping("/footprint")
@Validated
@Slf4j
public class FootprintController {

	@Reference
	private LitemallFootprintService footprintService;

	@Reference
	private LitemallGoodsService goodsService;

	/**
	 * 删除用户足迹
	 * @param userId 用户ID
	 * @param body 请求内容， { id: xxx }
	 * @return 删除操作结果
	 */
	@ApiOperation("删除用户足迹")
	@ApiImplicitParam("请求内容， { id: xxx }")
	@PostMapping("delete")
	public ResultJson delete(@LoginUser Integer userId, @RequestBody String body) {
		if (userId == null) {
			return ResponseUtil.unlogin();
		}
		if (body == null) {
			return ResponseUtil.badArgument();
		}

		Integer footprintId = JacksonUtil.parseInteger(body, "id");
		if (footprintId == null) {
			return ResponseUtil.badArgument();
		}
		LitemallFootprintDTO footprint = footprintService.findById(userId, footprintId);

		if (footprint == null) {
			return ResponseUtil.badArgumentValue();
		}
		if (!footprint.getUserId().equals(userId)) {
			return ResponseUtil.badArgumentValue();
		}

		footprintService.deleteById(footprintId);
		return ResponseUtil.ok();
	}

	/**
	 * 用户足迹列表
	 * @param page 分页页数
	 * @param limit 分页大小
	 * @return 用户足迹列表
	 */
	@ApiOperation("用户足迹列表")
	@GetMapping("list")
	public ResultJson<Page<FootprintVo>> list(@LoginUser Integer userId,
			@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer limit) {
		if (userId == null) {
			return ResponseUtil.unlogin();
		}

		PageInfo<LitemallFootprintDTO> pageInfo = footprintService.queryByAddTime(userId,
				page, limit);

		if (pageInfo == null) {
			return ResponseUtil.ok();
		}

		List<LitemallFootprintDTO> footprintList = pageInfo.getList();

		List<FootprintVo> footprintVoList = new ArrayList<>(footprintList.size());
		for (LitemallFootprintDTO footprint : footprintList) {
			FootprintVo vo = new FootprintVo();
			vo.setId(footprint.getId());
			vo.setGoodsId(footprint.getGoodsId());
			vo.setAddTime(footprint.getAddTime());
			LitemallGoodsDTO goods = goodsService.findById(footprint.getGoodsId());
			vo.setName(goods.getName());
			vo.setBrief(goods.getBrief());
			vo.setPicUrl(goods.getPicUrl());
			vo.setRetailPrice(goods.getRetailPrice());

			footprintVoList.add(vo);
		}

		return ResponseUtil.okList(footprintVoList, pageInfo);
	}

}
