package com.little.g.springcloud.admin.web.controller.mall;

import com.github.pagehelper.PageInfo;
import com.little.g.springcloud.admin.web.annotation.RequiresPermissions;
import com.little.g.springcloud.admin.web.annotation.RequiresPermissionsDesc;
import com.little.g.springcloud.common.ResultJson;
import com.little.g.springcloud.common.dto.Page;
import com.little.g.springcloud.common.utils.ResponseUtil;
import com.little.g.springcloud.mall.api.LitemallAdService;
import com.little.g.springcloud.mall.dto.LitemallAdDTO;
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

@Api("广告管理")
@RestController
@RequestMapping("/admin/ad")
@Validated
public class AdminAdController {

	private final Log logger = LogFactory.getLog(AdminAdController.class);

	@Reference
	private LitemallAdService adService;

	@ApiOperation("分页查询广告")
	@RequiresPermissions("admin:ad:list")
	@RequiresPermissionsDesc(menu = { "推广管理", "广告管理" }, button = "查询")
	@GetMapping("/list")
	public ResultJson<Page<LitemallAdDTO>> list(String name, String content,
			@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer limit,
			@Sort @RequestParam(defaultValue = "add_time") String sort,
			@Order @RequestParam(defaultValue = "desc") String order) {
		PageInfo<LitemallAdDTO> pageInfo = adService.querySelective(name, content, page,
				limit, sort, order);
		return ResponseUtil.okPage(pageInfo);
	}

	private ResultJson validate(LitemallAdDTO ad) {
		String name = ad.getName();
		if (StringUtils.isEmpty(name)) {
			return ResponseUtil.badArgument();
		}
		String content = ad.getContent();
		if (StringUtils.isEmpty(content)) {
			return ResponseUtil.badArgument();
		}
		return null;
	}

	@ApiOperation("添加广告")
	@RequiresPermissions("admin:ad:create")
	@RequiresPermissionsDesc(menu = { "推广管理", "广告管理" }, button = "添加")
	@PostMapping("/create")
	public ResultJson create(@RequestBody LitemallAdDTO ad) {
		ResultJson error = validate(ad);
		if (error != null) {
			return error;
		}
		adService.add(ad);
		return ResponseUtil.ok(ad);
	}

	@ApiOperation("广告详情")
	@RequiresPermissions("admin:ad:read")
	@RequiresPermissionsDesc(menu = { "推广管理", "广告管理" }, button = "详情")
	@GetMapping("/read")
	public ResultJson<LitemallAdDTO> read(@RequestParam @NotNull Integer id) {
		LitemallAdDTO ad = adService.findById(id);
		return ResponseUtil.ok(ad);
	}

	@ApiOperation("更新广告")
	@RequiresPermissions("admin:ad:update")
	@RequiresPermissionsDesc(menu = { "推广管理", "广告管理" }, button = "编辑")
	@PostMapping("/update")
	public ResultJson update(@RequestBody LitemallAdDTO ad) {
		ResultJson error = validate(ad);
		if (error != null) {
			return error;
		}
		if (adService.updateById(ad) == 0) {
			return ResponseUtil.updatedDataFailed();
		}

		return ResponseUtil.ok(ad);
	}

	@ApiOperation("删除广告")
	@RequiresPermissions("admin:ad:delete")
	@RequiresPermissionsDesc(menu = { "推广管理", "广告管理" }, button = "删除")
	@PostMapping("/delete")
	public ResultJson delete(@RequestBody LitemallAdDTO ad) {
		Integer id = ad.getId();
		if (id == null) {
			return ResponseUtil.badArgument();
		}
		adService.deleteById(id);
		return ResponseUtil.ok();
	}

}
