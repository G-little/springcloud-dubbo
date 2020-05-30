package com.little.g.springcloud.mall.web.controller;

import com.github.pagehelper.PageInfo;
import com.little.g.springcloud.common.utils.JacksonUtil;
import com.little.g.springcloud.common.utils.ResponseUtil;
import com.little.g.springcloud.common.web.annotation.LoginUser;
import com.little.g.springcloud.mall.api.LitemallCollectService;
import com.little.g.springcloud.mall.api.LitemallGoodsService;
import com.little.g.springcloud.mall.dto.LitemallCollectDTO;
import com.little.g.springcloud.mall.dto.LitemallGoodsDTO;
import com.little.g.springcloud.mall.validator.Order;
import com.little.g.springcloud.mall.validator.Sort;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户收藏服务
 */
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
	@GetMapping("list")
	public Object list(@LoginUser Integer userId, @NotNull Byte type,
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

		List<Object> collects = new ArrayList<>(collectList.size());
		for (LitemallCollectDTO collect : collectList) {
			Map<String, Object> c = new HashMap<String, Object>();
			c.put("id", collect.getId());
			c.put("type", collect.getType());
			c.put("valueId", collect.getValueId());

			LitemallGoodsDTO goods = goodsService.findById(collect.getValueId());
			c.put("name", goods.getName());
			c.put("brief", goods.getBrief());
			c.put("picUrl", goods.getPicUrl());
			c.put("retailPrice", goods.getRetailPrice());

			collects.add(c);
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
	@PostMapping("addordelete")
	public Object addordelete(@LoginUser Integer userId, @RequestBody String body) {
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
