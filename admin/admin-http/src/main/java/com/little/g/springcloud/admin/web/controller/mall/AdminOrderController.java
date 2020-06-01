package com.little.g.springcloud.admin.web.controller.mall;

import com.little.g.springcloud.admin.web.annotation.RequiresPermissions;
import com.little.g.springcloud.admin.web.annotation.RequiresPermissionsDesc;
import com.little.g.springcloud.admin.web.manager.AdminOrderManager;
import com.little.g.springcloud.common.utils.ResponseUtil;
import com.little.g.springcloud.mall.api.ExpressService;
import com.little.g.springcloud.mall.validator.Order;
import com.little.g.springcloud.mall.validator.Sort;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/admin/order")
@Validated
public class AdminOrderController {

	private final Log logger = LogFactory.getLog(AdminOrderController.class);

	@Resource
	private AdminOrderManager adminOrderManager;

	@Reference
	private ExpressService expressService;

	/**
	 * 查询订单
	 * @param userId
	 * @param orderSn
	 * @param orderStatusArray
	 * @param page
	 * @param limit
	 * @param sort
	 * @param order
	 * @return
	 */
	@RequiresPermissions("admin:order:list")
	@RequiresPermissionsDesc(menu = { "商场管理", "订单管理" }, button = "查询")
	@GetMapping("/list")
	public Object list(Integer userId, String orderSn,
			@RequestParam(required = false) @DateTimeFormat(
					iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
			@RequestParam(required = false) @DateTimeFormat(
					iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end,
			@RequestParam(required = false) List<Short> orderStatusArray,
			@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer limit,
			@Sort @RequestParam(defaultValue = "add_time") String sort,
			@Order @RequestParam(defaultValue = "desc") String order) {
		return adminOrderManager.list(userId, orderSn, start, end, orderStatusArray, page,
				limit, sort, order);
	}

	/**
	 * 查询物流公司
	 * @return
	 */
	@GetMapping("/channel")
	public Object channel() {
		return ResponseUtil.ok(expressService.getVendors());
	}

	/**
	 * 订单详情
	 * @param id
	 * @return
	 */
	@RequiresPermissions("admin:order:read")
	@RequiresPermissionsDesc(menu = { "商场管理", "订单管理" }, button = "详情")
	@GetMapping("/detail")
	public Object detail(@NotNull Integer id) {
		return adminOrderManager.detail(id);
	}

	/**
	 * 订单退款
	 * @param body 订单信息，{ orderId：xxx }
	 * @return 订单退款操作结果
	 */
	@RequiresPermissions("admin:order:refund")
	@RequiresPermissionsDesc(menu = { "商场管理", "订单管理" }, button = "订单退款")
	@PostMapping("/refund")
	public Object refund(@RequestBody String body) {
		return adminOrderManager.refund(body);
	}

	/**
	 * 发货
	 * @param body 订单信息，{ orderId：xxx, shipSn: xxx, shipChannel: xxx }
	 * @return 订单操作结果
	 */
	@RequiresPermissions("admin:order:ship")
	@RequiresPermissionsDesc(menu = { "商场管理", "订单管理" }, button = "订单发货")
	@PostMapping("/ship")
	public Object ship(@RequestBody String body) {
		return adminOrderManager.ship(body);
	}

	/**
	 * 删除订单
	 * @param body 订单信息，{ orderId：xxx }
	 * @return 订单操作结果
	 */
	@RequiresPermissions("admin:order:delete")
	@RequiresPermissionsDesc(menu = { "商场管理", "订单管理" }, button = "订单删除")
	@PostMapping("/delete")
	public Object delete(@RequestBody String body) {
		return adminOrderManager.delete(body);
	}

	/**
	 * 回复订单商品
	 * @param body 订单信息，{ orderId：xxx }
	 * @return 订单操作结果
	 */
	@RequiresPermissions("admin:order:reply")
	@RequiresPermissionsDesc(menu = { "商场管理", "订单管理" }, button = "订单商品回复")
	@PostMapping("/reply")
	public Object reply(@RequestBody String body) {
		return adminOrderManager.reply(body);
	}

}
