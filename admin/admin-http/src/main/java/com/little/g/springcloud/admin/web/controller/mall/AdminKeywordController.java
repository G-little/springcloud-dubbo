package com.little.g.springcloud.admin.web.controller.mall;

import com.github.pagehelper.PageInfo;
import com.little.g.springcloud.admin.web.annotation.RequiresPermissions;
import com.little.g.springcloud.admin.web.annotation.RequiresPermissionsDesc;
import com.little.g.springcloud.common.utils.ResponseUtil;
import com.little.g.springcloud.mall.api.LitemallKeywordService;
import com.little.g.springcloud.mall.dto.LitemallKeywordDTO;
import com.little.g.springcloud.mall.validator.Order;
import com.little.g.springcloud.mall.validator.Sort;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/admin/keyword")
@Validated
public class AdminKeywordController {

	private final Log logger = LogFactory.getLog(AdminKeywordController.class);

	@Reference
	private LitemallKeywordService keywordService;

	@RequiresPermissions("admin:keyword:list")
	@RequiresPermissionsDesc(menu = { "商场管理", "关键词" }, button = "查询")
	@GetMapping("/list")
	public Object list(String keyword, String url,
			@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer limit,
			@Sort @RequestParam(defaultValue = "add_time") String sort,
			@Order @RequestParam(defaultValue = "desc") String order) {
		PageInfo<LitemallKeywordDTO> pageInfo = keywordService.querySelective(keyword,
				url, page, limit, sort, order);
		return ResponseUtil.okPage(pageInfo);
	}

	private Object validate(LitemallKeywordDTO keywords) {
		String keyword = keywords.getKeyword();
		if (StringUtils.isEmpty(keyword)) {
			return ResponseUtil.badArgument();
		}
		return null;
	}

	@RequiresPermissions("admin:keyword:create")
	@RequiresPermissionsDesc(menu = { "商场管理", "关键词" }, button = "添加")
	@PostMapping("/create")
	public Object create(@RequestBody LitemallKeywordDTO keyword) {
		Object error = validate(keyword);
		if (error != null) {
			return error;
		}
		keywordService.add(keyword);
		return ResponseUtil.ok(keyword);
	}

	@RequiresPermissions("admin:keyword:read")
	@RequiresPermissionsDesc(menu = { "商场管理", "关键词" }, button = "详情")
	@GetMapping("/read")
	public Object read(@NotNull Integer id) {
		LitemallKeywordDTO keyword = keywordService.findById(id);
		return ResponseUtil.ok(keyword);
	}

	@RequiresPermissions("admin:keyword:update")
	@RequiresPermissionsDesc(menu = { "商场管理", "关键词" }, button = "编辑")
	@PostMapping("/update")
	public Object update(@RequestBody LitemallKeywordDTO keyword) {
		Object error = validate(keyword);
		if (error != null) {
			return error;
		}
		if (keywordService.updateById(keyword) == 0) {
			return ResponseUtil.updatedDataFailed();
		}
		return ResponseUtil.ok(keyword);
	}

	@RequiresPermissions("admin:keyword:delete")
	@RequiresPermissionsDesc(menu = { "商场管理", "关键词" }, button = "删除")
	@PostMapping("/delete")
	public Object delete(@RequestBody LitemallKeywordDTO keyword) {
		Integer id = keyword.getId();
		if (id == null) {
			return ResponseUtil.badArgument();
		}
		keywordService.deleteById(id);
		return ResponseUtil.ok();
	}

}
