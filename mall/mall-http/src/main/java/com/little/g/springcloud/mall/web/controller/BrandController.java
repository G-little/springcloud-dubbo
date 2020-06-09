package com.little.g.springcloud.mall.web.controller;

import com.github.pagehelper.PageInfo;
import com.little.g.springcloud.common.ResultJson;
import com.little.g.springcloud.common.dto.Page;
import com.little.g.springcloud.common.utils.ResponseUtil;
import com.little.g.springcloud.mall.api.LitemallBrandService;
import com.little.g.springcloud.mall.dto.LitemallBrandDTO;
import com.little.g.springcloud.mall.validator.Order;
import com.little.g.springcloud.mall.validator.Sort;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * 专题服务
 */
@Api("品牌")
@RestController
@RequestMapping("/brand")
@Validated
@Slf4j
public class BrandController {

	@Reference
	private LitemallBrandService brandService;

	/**
	 * 品牌列表
	 * @param page 分页页数
	 * @param limit 分页大小
	 * @return 品牌列表
	 */
	@ApiOperation(value = "品牌列表", notes = "分页查询品牌列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "page", value = "分页", dataType = "int",
					required = false, defaultValue = "1",example = "1"),
			@ApiImplicitParam(name = "limit", value = "单页条数", dataType = "int",
					required = false, defaultValue = "10",example = "10"),
			@ApiImplicitParam(name = "sort", value = "排序字段", dataType = "String",
					required = false, defaultValue = "add_time") })
	@GetMapping("list")
	public ResultJson<Page<LitemallBrandDTO>> list(
			@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer limit,
			@Sort @RequestParam(defaultValue = "add_time") String sort,
			@Order @RequestParam(defaultValue = "desc") String order) {
		PageInfo<LitemallBrandDTO> brandPage = brandService.query(page, limit, sort,
				order);
		return ResponseUtil.okPage(brandPage);
	}

	/**
	 * 品牌详情
	 * @param id 品牌ID
	 * @return 品牌详情
	 */
	@ApiOperation(value = "品牌详情", notes = "品牌详情")
	@GetMapping("detail")
	public ResultJson<LitemallBrandDTO> detail(@NotNull Integer id) {
		LitemallBrandDTO entity = brandService.findById(id);
		if (entity == null) {
			return ResponseUtil.badArgumentValue();
		}

		return ResponseUtil.ok(entity);
	}

}
