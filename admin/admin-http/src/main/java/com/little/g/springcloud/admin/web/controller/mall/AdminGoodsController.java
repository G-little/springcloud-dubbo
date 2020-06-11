package com.little.g.springcloud.admin.web.controller.mall;

import com.little.g.springcloud.admin.web.annotation.RequiresPermissions;
import com.little.g.springcloud.admin.web.annotation.RequiresPermissionsDesc;
import com.little.g.springcloud.admin.web.manager.mall.AdminGoodsManager;
import com.little.g.springcloud.admin.web.vo.CategoryBrandVo;
import com.little.g.springcloud.admin.web.vo.GoodsAllinoneVo;
import com.little.g.springcloud.admin.web.vo.GoodsDetailVo;
import com.little.g.springcloud.common.ResultJson;
import com.little.g.springcloud.common.dto.Page;
import com.little.g.springcloud.mall.dto.LitemallGoodsDTO;
import com.little.g.springcloud.mall.validator.Order;
import com.little.g.springcloud.mall.validator.Sort;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

@Api("商品管理")
@RestController
@RequestMapping("/admin/goods")
@Validated
public class AdminGoodsController {

	private final Log logger = LogFactory.getLog(AdminGoodsController.class);

	@Resource
	private AdminGoodsManager adminGoodsManager;

	/**
	 * 查询商品
	 * @param goodsId
	 * @param goodsSn
	 * @param name
	 * @param page
	 * @param limit
	 * @param sort
	 * @param order
	 * @return
	 */
	@ApiOperation("商品分页查询")
	@RequiresPermissions("admin:goods:list")
	@RequiresPermissionsDesc(menu = { "商品管理", "商品管理" }, button = "查询")
	@GetMapping("/list")
	public ResultJson<Page<LitemallGoodsDTO>> list(Integer goodsId, String goodsSn,
			String name, @RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer limit,
			@Sort @RequestParam(defaultValue = "add_time") String sort,
			@Order @RequestParam(defaultValue = "desc") String order) {
		return adminGoodsManager.list(goodsId, goodsSn, name, page, limit, sort, order);
	}

	@ApiOperation("品牌和类目")
	@GetMapping("/catAndBrand")
	public ResultJson<CategoryBrandVo> list2() {
		return adminGoodsManager.list2();
	}

	/**
	 * 编辑商品
	 * @param goodsAllinone
	 * @return
	 */
	@RequiresPermissions("admin:goods:update")
	@RequiresPermissionsDesc(menu = { "商品管理", "商品管理" }, button = "编辑")
	@PostMapping("/update")
	public ResultJson update(@RequestBody GoodsAllinoneVo goodsAllinone) {
		return adminGoodsManager.update(goodsAllinone);
	}

	/**
	 * 删除商品
	 * @param goods
	 * @return
	 */
	@ApiOperation("删除商品")
	@RequiresPermissions("admin:goods:delete")
	@RequiresPermissionsDesc(menu = { "商品管理", "商品管理" }, button = "删除")
	@PostMapping("/delete")
	public ResultJson delete(@RequestBody LitemallGoodsDTO goods) {
		return adminGoodsManager.delete(goods);
	}

	/**
	 * 添加商品
	 * @param goodsAllinone
	 * @return
	 */
	@ApiOperation("创建商品")
	@RequiresPermissions("admin:goods:create")
	@RequiresPermissionsDesc(menu = { "商品管理", "商品管理" }, button = "上架")
	@PostMapping("/create")
	public ResultJson<CategoryBrandVo> create(
			@RequestBody GoodsAllinoneVo goodsAllinone) {
		return adminGoodsManager.create(goodsAllinone);
	}

	/**
	 * 商品详情
	 * @param id
	 * @return
	 */
	@ApiOperation("商品详情")
	@RequiresPermissions("admin:goods:read")
	@RequiresPermissionsDesc(menu = { "商品管理", "商品管理" }, button = "详情")
	@GetMapping("/detail")
	public ResultJson<GoodsDetailVo> detail(@NotNull Integer id) {
		return adminGoodsManager.detail(id);

	}

}
