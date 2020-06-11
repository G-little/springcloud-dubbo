package com.little.g.springcloud.admin.web.controller.mall;

import com.github.pagehelper.PageInfo;
import com.little.g.springcloud.admin.web.annotation.RequiresPermissions;
import com.little.g.springcloud.admin.web.annotation.RequiresPermissionsDesc;
import com.little.g.springcloud.admin.web.vo.TopicDetailVo;
import com.little.g.springcloud.common.ResultJson;
import com.little.g.springcloud.common.dto.Page;
import com.little.g.springcloud.common.utils.JacksonUtil;
import com.little.g.springcloud.common.utils.ResponseUtil;
import com.little.g.springcloud.mall.api.LitemallGoodsService;
import com.little.g.springcloud.mall.api.LitemallTopicService;
import com.little.g.springcloud.mall.dto.LitemallGoodsDTO;
import com.little.g.springcloud.mall.dto.LitemallTopicDTO;
import com.little.g.springcloud.mall.validator.Order;
import com.little.g.springcloud.mall.validator.Sort;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Api("专题管理")
@RestController
@RequestMapping("/admin/topic")
@Validated
public class AdminTopicController {

	private final Log logger = LogFactory.getLog(AdminTopicController.class);

	@Reference
	private LitemallTopicService topicService;

	@Reference
	private LitemallGoodsService goodsService;

	@ApiOperation("专题分页管理")
	@RequiresPermissions("admin:topic:list")
	@RequiresPermissionsDesc(menu = { "推广管理", "专题管理" }, button = "查询")
	@GetMapping("/list")
	public ResultJson<Page<LitemallTopicDTO>> list(String title, String subtitle,
			@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer limit,
			@Sort(accepts = { "id", "add_time", "price" }) @RequestParam(
					defaultValue = "add_time") String sort,
			@Order @RequestParam(defaultValue = "desc") String order) {
		PageInfo<LitemallTopicDTO> pageInfo = topicService.querySelective(title, subtitle,
				page, limit, sort, order);
		return ResponseUtil.okPage(pageInfo);
	}

	private ResultJson validate(LitemallTopicDTO topic) {
		String title = topic.getTitle();
		if (StringUtils.isEmpty(title)) {
			return ResponseUtil.badArgument();
		}
		String content = topic.getContent();
		if (StringUtils.isEmpty(content)) {
			return ResponseUtil.badArgument();
		}
		BigDecimal price = topic.getPrice();
		if (price == null) {
			return ResponseUtil.badArgument();
		}
		return null;
	}

	@ApiOperation("专题添加")
	@RequiresPermissions("admin:topic:create")
	@RequiresPermissionsDesc(menu = { "推广管理", "专题管理" }, button = "添加")
	@PostMapping("/create")
	public ResultJson<LitemallTopicDTO> create(@RequestBody LitemallTopicDTO topic) {
		ResultJson error = validate(topic);
		if (error != null) {
			return error;
		}
		topicService.add(topic);
		return ResponseUtil.ok(topic);
	}

	@ApiOperation("专题详情")
	@RequiresPermissions("admin:topic:read")
	@RequiresPermissionsDesc(menu = { "推广管理", "专题管理" }, button = "详情")
	@GetMapping("/read")
	public ResultJson<TopicDetailVo> read(@NotNull Integer id) {
		LitemallTopicDTO topic = topicService.findById(id);
		Integer[] goodsIds = topic.getGoods();
		List<LitemallGoodsDTO> goodsList = null;
		if (goodsIds == null || goodsIds.length == 0) {
			goodsList = new ArrayList<>();
		}
		else {
			goodsList = goodsService.queryByIds(goodsIds);
		}
		TopicDetailVo data = new TopicDetailVo();
		data.setTopic(topic);
		data.setGoodsList(goodsList);
		return ResponseUtil.ok(data);
	}

	@ApiOperation("专题更新")
	@RequiresPermissions("admin:topic:update")
	@RequiresPermissionsDesc(menu = { "推广管理", "专题管理" }, button = "编辑")
	@PostMapping("/update")
	public ResultJson<LitemallTopicDTO> update(@RequestBody LitemallTopicDTO topic) {
		ResultJson error = validate(topic);
		if (error != null) {
			return error;
		}
		if (topicService.updateById(topic) == 0) {
			return ResponseUtil.updatedDataFailed();
		}
		return ResponseUtil.ok(topic);
	}

	@ApiOperation("专题删除")
	@RequiresPermissions("admin:topic:delete")
	@RequiresPermissionsDesc(menu = { "推广管理", "专题管理" }, button = "删除")
	@PostMapping("/delete")
	public Object delete(@RequestBody LitemallTopicDTO topic) {
		topicService.deleteById(topic.getId());
		return ResponseUtil.ok();
	}

	@ApiOperation("专题批量删除")
	@RequiresPermissions("admin:topic:batch-delete")
	@RequiresPermissionsDesc(menu = { "推广管理", "专题管理" }, button = "批量删除")
	@PostMapping("/batch-delete")
	public Object batchDelete(@RequestBody String body) {
		List<Integer> ids = JacksonUtil.parseIntegerList(body, "ids");
		topicService.deleteByIds(ids);
		return ResponseUtil.ok();
	}

}
