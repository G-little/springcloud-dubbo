package com.little.g.springcloud.admin.web.controller.mall;

import com.little.g.springcloud.admin.web.annotation.RequiresPermissions;
import com.little.g.springcloud.admin.web.annotation.RequiresPermissionsDesc;
import com.little.g.springcloud.admin.web.vo.StatVo;
import com.little.g.springcloud.common.ResultJson;
import com.little.g.springcloud.common.utils.ResponseUtil;
import com.little.g.springcloud.mall.api.StatService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Api("统计管理")
@RestController
@RequestMapping("/admin/stat")
@Validated
public class AdminStatController {

	private final Log logger = LogFactory.getLog(AdminStatController.class);

	@Reference
	private StatService statService;

	@ApiOperation("用户统计")
	@RequiresPermissions("admin:stat:user")
	@RequiresPermissionsDesc(menu = { "统计管理", "用户统计" }, button = "查询")
	@GetMapping("/user")
	public ResultJson<StatVo> statUser() {
		List<Map> rows = statService.statUser();
		String[] columns = new String[] { "day", "users" };
		StatVo statVo = new StatVo();
		statVo.setColumns(columns);
		statVo.setRows(rows);
		return ResponseUtil.ok(statVo);
	}

	@ApiOperation("点单统计")
	@RequiresPermissions("admin:stat:order")
	@RequiresPermissionsDesc(menu = { "统计管理", "订单统计" }, button = "查询")
	@GetMapping("/order")
	public ResultJson<StatVo> statOrder() {
		List<Map> rows = statService.statOrder();
		String[] columns = new String[] { "day", "orders", "customers", "amount", "pcr" };
		StatVo statVo = new StatVo();
		statVo.setColumns(columns);
		statVo.setRows(rows);

		return ResponseUtil.ok(statVo);
	}

	@ApiOperation("商品统计")
	@RequiresPermissions("admin:stat:goods")
	@RequiresPermissionsDesc(menu = { "统计管理", "商品统计" }, button = "查询")
	@GetMapping("/goods")
	public ResultJson<StatVo> statGoods() {
		List<Map> rows = statService.statGoods();
		String[] columns = new String[] { "day", "orders", "products", "amount" };
		StatVo statVo = new StatVo();
		statVo.setColumns(columns);
		statVo.setRows(rows);
		return ResponseUtil.ok(statVo);
	}

}
