package com.little.g.springcloud.admin.web.controller.mall;

import com.little.g.springcloud.admin.web.annotation.RequiresPermissions;
import com.little.g.springcloud.admin.web.annotation.RequiresPermissionsDesc;
import com.little.g.springcloud.common.ResultJson;
import com.little.g.springcloud.common.utils.ResponseUtil;
import com.little.g.springcloud.mall.api.LitemallBrandService;
import com.little.g.springcloud.mall.dto.LitemallBrandDTO;
import com.little.g.springcloud.mall.validator.Order;
import com.little.g.springcloud.mall.validator.Sort;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Api("品牌管理")
@RestController
@RequestMapping("/admin/brand")
@Validated
public class AdminBrandController {

	private final Log logger = LogFactory.getLog(AdminBrandController.class);

	@Reference
	private LitemallBrandService brandService;

	@ApiOperation("品牌分页查询")
	@RequiresPermissions("admin:brand:list")
	@RequiresPermissionsDesc(menu = { "商场管理", "品牌管理" }, button = "查询")
	@GetMapping("/list")
	public Object list(String id, String name,
			@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer limit,
			@Sort @RequestParam(defaultValue = "add_time") String sort,
			@Order @RequestParam(defaultValue = "desc") String order) {
		List<LitemallBrandDTO> brandList = brandService.querySelective(id, name, page,
				limit, sort, order);
		return ResponseUtil.okList(brandList);
	}

	private ResultJson validate(LitemallBrandDTO brand) {
		String name = brand.getName();
		if (StringUtils.isEmpty(name)) {
			return ResponseUtil.badArgument();
		}

		String desc = brand.getDesc();
		if (StringUtils.isEmpty(desc)) {
			return ResponseUtil.badArgument();
		}

		BigDecimal price = brand.getFloorPrice();
		if (price == null) {
			return ResponseUtil.badArgument();
		}
		return null;
	}

	@ApiOperation("品牌添加")
	@RequiresPermissions("admin:brand:create")
	@RequiresPermissionsDesc(menu = { "商场管理", "品牌管理" }, button = "添加")
	@PostMapping("/create")
	public Object create(@RequestBody LitemallBrandDTO brand) {
		Object error = validate(brand);
		if (error != null) {
			return error;
		}
		brandService.add(brand);
		return ResponseUtil.ok(brand);
	}

	@ApiOperation("品牌详情")
	@RequiresPermissions("admin:brand:read")
	@RequiresPermissionsDesc(menu = { "商场管理", "品牌管理" }, button = "详情")
	@GetMapping("/read")
	public Object read(@NotNull Integer id) {
		LitemallBrandDTO brand = brandService.findById(id);
		return ResponseUtil.ok(brand);
	}

	@ApiOperation("品牌更新")
	@RequiresPermissions("admin:brand:update")
	@RequiresPermissionsDesc(menu = { "商场管理", "品牌管理" }, button = "编辑")
	@PostMapping("/update")
	public ResultJson update(@RequestBody LitemallBrandDTO brand) {
		ResultJson error = validate(brand);
		if (error != null) {
			return error;
		}
		if (brandService.updateById(brand) == 0) {
			return ResponseUtil.updatedDataFailed();
		}
		return ResponseUtil.ok(brand);
	}

	@ApiOperation("品牌删除")
	@RequiresPermissions("admin:brand:delete")
	@RequiresPermissionsDesc(menu = { "商场管理", "品牌管理" }, button = "删除")
	@PostMapping("/delete")
	public ResultJson delete(@RequestBody LitemallBrandDTO brand) {
		Integer id = brand.getId();
		if (id == null) {
			return ResponseUtil.badArgument();
		}
		brandService.deleteById(id);
		return ResponseUtil.ok();
	}

}
