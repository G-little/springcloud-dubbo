package com.little.g.springcloud.admin.web.controller.mall;

import com.github.pagehelper.PageInfo;
import com.little.g.springcloud.admin.web.annotation.RequiresPermissions;
import com.little.g.springcloud.admin.web.annotation.RequiresPermissionsDesc;
import com.little.g.springcloud.common.ResultJson;
import com.little.g.springcloud.common.dto.Page;
import com.little.g.springcloud.common.utils.ResponseUtil;
import com.little.g.springcloud.mall.api.LitemallStorageService;
import com.little.g.springcloud.mall.api.StorageService;
import com.little.g.springcloud.mall.dto.LitemallStorageDTO;
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
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.IOException;

@Api("对象存储")
@RestController
@RequestMapping("/admin/storage")
@Validated
public class AdminStorageController {

	private final Log logger = LogFactory.getLog(AdminStorageController.class);

	@Reference
	private StorageService storageService;

	@Reference
	private LitemallStorageService litemallStorageService;

	@ApiOperation("对象存储分页查询")
	@RequiresPermissions("admin:storage:list")
	@RequiresPermissionsDesc(menu = { "系统管理", "对象存储" }, button = "查询")
	@GetMapping("/list")
	public ResultJson<Page<LitemallStorageDTO>> list(String key, String name,
			@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer limit,
			@Sort @RequestParam(defaultValue = "add_time") String sort,
			@Order @RequestParam(defaultValue = "desc") String order) {
		PageInfo<LitemallStorageDTO> pageInfo = litemallStorageService.querySelective(key,
				name, page, limit, sort, order);
		return ResponseUtil.okPage(pageInfo);
	}

	@ApiOperation("对象存储上传")
	@RequiresPermissions("admin:storage:create")
	@RequiresPermissionsDesc(menu = { "系统管理", "对象存储" }, button = "上传")
	@PostMapping("/create")
	public ResultJson<LitemallStorageDTO> create(@RequestParam("file") MultipartFile file)
			throws IOException {
		String originalFilename = file.getOriginalFilename();
		LitemallStorageDTO litemallStorage = storageService.store(file.getInputStream(),
				file.getSize(), file.getContentType(), originalFilename);
		return ResponseUtil.ok(litemallStorage);
	}

	@ApiOperation("对象存储详情")
	@RequiresPermissions("admin:storage:read")
	@RequiresPermissionsDesc(menu = { "系统管理", "对象存储" }, button = "详情")
	@PostMapping("/read")
	public ResultJson read(@NotNull Integer id) {
		LitemallStorageDTO storageInfo = litemallStorageService.findById(id);
		if (storageInfo == null) {
			return ResponseUtil.badArgumentValue();
		}
		return ResponseUtil.ok(storageInfo);
	}

	@ApiOperation("对象存储编辑")
	@RequiresPermissions("admin:storage:update")
	@RequiresPermissionsDesc(menu = { "系统管理", "对象存储" }, button = "编辑")
	@PostMapping("/update")
	public Object update(@RequestBody LitemallStorageDTO litemallStorage) {
		if (litemallStorageService.update(litemallStorage) == 0) {
			return ResponseUtil.updatedDataFailed();
		}
		return ResponseUtil.ok(litemallStorage);
	}

	@ApiOperation("对象存储删除")
	@RequiresPermissions("admin:storage:delete")
	@RequiresPermissionsDesc(menu = { "系统管理", "对象存储" }, button = "删除")
	@PostMapping("/delete")
	public Object delete(@RequestBody LitemallStorageDTO litemallStorage) {
		String key = litemallStorage.getKey();
		if (StringUtils.isEmpty(key)) {
			return ResponseUtil.badArgument();
		}
		litemallStorageService.deleteByKey(key);
		storageService.delete(key);
		return ResponseUtil.ok();
	}

}
