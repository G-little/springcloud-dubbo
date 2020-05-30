package com.little.g.springcloud.admin.web.controller.mall;

import com.github.pagehelper.PageInfo;
import com.little.g.springcloud.admin.AdminErrorCodes;
import com.little.g.springcloud.admin.web.annotation.RequiresPermissions;
import com.little.g.springcloud.admin.web.annotation.RequiresPermissionsDesc;
import com.little.g.springcloud.admin.web.task.GrouponRuleExpiredTask;
import com.little.g.springcloud.common.task.TaskService;
import com.little.g.springcloud.common.utils.ResponseUtil;
import com.little.g.springcloud.mall.api.LitemallGoodsService;
import com.little.g.springcloud.mall.api.LitemallGrouponRulesService;
import com.little.g.springcloud.mall.api.LitemallGrouponService;
import com.little.g.springcloud.mall.dto.LitemallGoodsDTO;
import com.little.g.springcloud.mall.dto.LitemallGrouponDTO;
import com.little.g.springcloud.mall.dto.LitemallGrouponRulesDTO;
import com.little.g.springcloud.mall.util.GrouponConstant;
import com.little.g.springcloud.mall.validator.Order;
import com.little.g.springcloud.mall.validator.Sort;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/groupon")
@Validated
public class AdminGrouponController {

	private final Log logger = LogFactory.getLog(AdminGrouponController.class);

	@Reference
	private LitemallGrouponRulesService rulesService;

	@Reference
	private LitemallGoodsService goodsService;

	@Reference
	private LitemallGrouponService grouponService;

	@Resource
	private TaskService taskService;

	@RequiresPermissions("admin:groupon:read")
	@RequiresPermissionsDesc(menu = { "推广管理", "团购管理" }, button = "详情")
	@GetMapping("/listRecord")
	public Object listRecord(String grouponRuleId,
			@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer limit,
			@Sort @RequestParam(defaultValue = "add_time") String sort,
			@Order @RequestParam(defaultValue = "desc") String order) {
		PageInfo<LitemallGrouponDTO> pageInfo = grouponService
				.querySelective(grouponRuleId, page, limit, sort, order);
		List<LitemallGrouponDTO> grouponList = pageInfo.getList();
		List<Map<String, Object>> groupons = new ArrayList<>();
		for (LitemallGrouponDTO groupon : grouponList) {
			try {
				Map<String, Object> recordData = new HashMap<>();
				List<LitemallGrouponDTO> subGrouponList = grouponService
						.queryJoinRecord(groupon.getId());
				LitemallGrouponRulesDTO rules = rulesService
						.findById(groupon.getRulesId());
				LitemallGoodsDTO goods = goodsService.findById(rules.getGoodsId());

				recordData.put("groupon", groupon);
				recordData.put("subGroupons", subGrouponList);
				recordData.put("rules", rules);
				recordData.put("goods", goods);

				groupons.add(recordData);
			}
			catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}

		return ResponseUtil.okList(groupons, pageInfo);
	}

	@RequiresPermissions("admin:groupon:list")
	@RequiresPermissionsDesc(menu = { "推广管理", "团购管理" }, button = "查询")
	@GetMapping("/list")
	public Object list(String goodsId, @RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer limit,
			@Sort @RequestParam(defaultValue = "add_time") String sort,
			@Order @RequestParam(defaultValue = "desc") String order) {
		List<LitemallGrouponRulesDTO> rulesList = rulesService.querySelective(goodsId,
				page, limit, sort, order);
		return ResponseUtil.okList(rulesList);
	}

	private Object validate(LitemallGrouponRulesDTO grouponRules) {
		Integer goodsId = grouponRules.getGoodsId();
		if (goodsId == null) {
			return ResponseUtil.badArgument();
		}
		BigDecimal discount = grouponRules.getDiscount();
		if (discount == null) {
			return ResponseUtil.badArgument();
		}
		Integer discountMember = grouponRules.getDiscountMember();
		if (discountMember == null) {
			return ResponseUtil.badArgument();
		}
		LocalDateTime expireTime = grouponRules.getExpireTime();
		if (expireTime == null) {
			return ResponseUtil.badArgument();
		}

		return null;
	}

	@RequiresPermissions("admin:groupon:update")
	@RequiresPermissionsDesc(menu = { "推广管理", "团购管理" }, button = "编辑")
	@PostMapping("/update")
	public Object update(@RequestBody LitemallGrouponRulesDTO grouponRules) {
		Object error = validate(grouponRules);
		if (error != null) {
			return error;
		}

		LitemallGrouponRulesDTO rules = rulesService.findById(grouponRules.getId());
		if (rules == null) {
			return ResponseUtil.badArgumentValue();
		}
		if (!rules.getStatus().equals(GrouponConstant.RULE_STATUS_ON)) {
			return ResponseUtil.fail(AdminErrorCodes.GROUPON_GOODS_OFFLINE, "团购已经下线");
		}

		Integer goodsId = grouponRules.getGoodsId();
		LitemallGoodsDTO goods = goodsService.findById(goodsId);
		if (goods == null) {
			return ResponseUtil.badArgumentValue();
		}

		grouponRules.setGoodsName(goods.getName());
		grouponRules.setPicUrl(goods.getPicUrl());

		if (rulesService.updateById(grouponRules) == 0) {
			return ResponseUtil.updatedDataFailed();
		}

		return ResponseUtil.ok();
	}

	@RequiresPermissions("admin:groupon:create")
	@RequiresPermissionsDesc(menu = { "推广管理", "团购管理" }, button = "添加")
	@PostMapping("/create")
	public Object create(@RequestBody LitemallGrouponRulesDTO grouponRules) {
		Object error = validate(grouponRules);
		if (error != null) {
			return error;
		}

		Integer goodsId = grouponRules.getGoodsId();
		LitemallGoodsDTO goods = goodsService.findById(goodsId);
		if (goods == null) {
			return ResponseUtil.fail(AdminErrorCodes.GROUPON_GOODS_UNKNOWN, "团购商品不存在");
		}
		if (rulesService.countByGoodsId(goodsId) > 0) {
			return ResponseUtil.fail(AdminErrorCodes.GROUPON_GOODS_EXISTED, "团购商品已经存在");
		}

		grouponRules.setGoodsName(goods.getName());
		grouponRules.setPicUrl(goods.getPicUrl());
		grouponRules.setStatus(GrouponConstant.RULE_STATUS_ON);
		rulesService.createRules(grouponRules);

		LocalDateTime now = LocalDateTime.now();
		LocalDateTime expire = grouponRules.getExpireTime();
		long delay = ChronoUnit.MILLIS.between(now, expire);
		// 团购过期任务
		taskService.addTask(new GrouponRuleExpiredTask(grouponRules.getId(), delay));

		return ResponseUtil.ok(grouponRules);
	}

	@RequiresPermissions("admin:groupon:delete")
	@RequiresPermissionsDesc(menu = { "推广管理", "团购管理" }, button = "删除")
	@PostMapping("/delete")
	public Object delete(@RequestBody LitemallGrouponRulesDTO grouponRules) {
		Integer id = grouponRules.getId();
		if (id == null) {
			return ResponseUtil.badArgument();
		}

		rulesService.delete(id);
		return ResponseUtil.ok();
	}

}
