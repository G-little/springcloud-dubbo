package com.little.g.springcloud.admin.web.controller.mall;

import com.github.pagehelper.PageInfo;
import com.little.g.springcloud.admin.web.annotation.RequiresPermissions;
import com.little.g.springcloud.admin.web.annotation.RequiresPermissionsDesc;
import com.little.g.springcloud.common.utils.ResponseUtil;
import com.little.g.springcloud.mall.api.LitemallIssueService;
import com.little.g.springcloud.mall.dto.LitemallIssueDTO;
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
@RequestMapping("/admin/issue")
@Validated
public class AdminIssueController {

	private final Log logger = LogFactory.getLog(AdminIssueController.class);

	@Reference
	private LitemallIssueService issueService;

	@RequiresPermissions("admin:issue:list")
	@RequiresPermissionsDesc(menu = { "商场管理", "通用问题" }, button = "查询")
	@GetMapping("/list")
	public Object list(String question, @RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer limit,
			@Sort @RequestParam(defaultValue = "add_time") String sort,
			@Order @RequestParam(defaultValue = "desc") String order) {
		PageInfo<LitemallIssueDTO> pageInfo = issueService.querySelective(question, page,
				limit, sort, order);
		return ResponseUtil.okPage(pageInfo);
	}

	private Object validate(LitemallIssueDTO issue) {
		String question = issue.getQuestion();
		if (StringUtils.isEmpty(question)) {
			return ResponseUtil.badArgument();
		}
		String answer = issue.getAnswer();
		if (StringUtils.isEmpty(answer)) {
			return ResponseUtil.badArgument();
		}
		return null;
	}

	@RequiresPermissions("admin:issue:create")
	@RequiresPermissionsDesc(menu = { "商场管理", "通用问题" }, button = "添加")
	@PostMapping("/create")
	public Object create(@RequestBody LitemallIssueDTO issue) {
		Object error = validate(issue);
		if (error != null) {
			return error;
		}
		issueService.add(issue);
		return ResponseUtil.ok(issue);
	}

	@RequiresPermissions("admin:issue:read")
	@GetMapping("/read")
	public Object read(@NotNull Integer id) {
		LitemallIssueDTO issue = issueService.findById(id);
		return ResponseUtil.ok(issue);
	}

	@RequiresPermissions("admin:issue:update")
	@RequiresPermissionsDesc(menu = { "商场管理", "通用问题" }, button = "编辑")
	@PostMapping("/update")
	public Object update(@RequestBody LitemallIssueDTO issue) {
		Object error = validate(issue);
		if (error != null) {
			return error;
		}
		if (issueService.updateById(issue) == 0) {
			return ResponseUtil.updatedDataFailed();
		}

		return ResponseUtil.ok(issue);
	}

	@RequiresPermissions("admin:issue:delete")
	@RequiresPermissionsDesc(menu = { "商场管理", "通用问题" }, button = "删除")
	@PostMapping("/delete")
	public Object delete(@RequestBody LitemallIssueDTO issue) {
		Integer id = issue.getId();
		if (id == null) {
			return ResponseUtil.badArgument();
		}
		issueService.deleteById(id);
		return ResponseUtil.ok();
	}

}
