package com.little.g.springcloud.admin.web.controller.mall;

import com.github.pagehelper.PageInfo;
import com.little.g.springcloud.admin.web.annotation.RequiresPermissions;
import com.little.g.springcloud.admin.web.annotation.RequiresPermissionsDesc;
import com.little.g.springcloud.common.ResultJson;
import com.little.g.springcloud.common.dto.Page;
import com.little.g.springcloud.common.utils.ResponseUtil;
import com.little.g.springcloud.mall.api.LitemallLogService;
import com.little.g.springcloud.mall.dto.LitemallLogDTO;
import com.little.g.springcloud.mall.validator.Order;
import com.little.g.springcloud.mall.validator.Sort;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api("操作日志")
@RestController
@RequestMapping("/admin/log")
@Validated
public class AdminLogController {

	private final Log logger = LogFactory.getLog(AdminLogController.class);

	@Reference
	private LitemallLogService logService;

	@ApiOperation("操作日志分页查询")
	@RequiresPermissions("admin:log:list")
	@RequiresPermissionsDesc(menu = { "系统管理", "操作日志" }, button = "查询")
	@GetMapping("/list")
	public ResultJson<Page<LitemallLogDTO>> list(String name,
			@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer limit,
			@Sort @RequestParam(defaultValue = "add_time") String sort,
			@Order @RequestParam(defaultValue = "desc") String order) {
		PageInfo<LitemallLogDTO> pageInfo = logService.querySelective(name, page, limit,
				sort, order);
		return ResponseUtil.okPage(pageInfo);
	}

}
