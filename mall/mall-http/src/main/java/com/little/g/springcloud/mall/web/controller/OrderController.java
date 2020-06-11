package com.little.g.springcloud.mall.web.controller;

import com.little.g.springcloud.common.ResultJson;
import com.little.g.springcloud.common.dto.Page;
import com.little.g.springcloud.common.web.annotation.LoginUser;
import com.little.g.springcloud.mall.validator.Order;
import com.little.g.springcloud.mall.validator.Sort;
import com.little.g.springcloud.mall.web.manager.OrderManager;
import com.little.g.springcloud.mall.web.vo.OrderDetailVo;
import com.little.g.springcloud.mall.web.vo.OrderVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

@Api("订单")
@RestController
@RequestMapping("/order")
@Validated
@Slf4j
public class OrderController {

	@Autowired
	private OrderManager orderManager;

	/**
	 * 订单列表
	 * @param userId 用户ID
	 * @param showType 显示类型，如果是0则是全部订单
	 * @param page 分页页数
	 * @param limit 分页大小
	 * @param sort 排序字段
	 * @param order 排序方式
	 * @return 订单列表
	 */
	@ApiOperation(value = "订单列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "showType",
					value = "显示类型，0，全部订单； 1，待付款； 2，待发货； 3，待收货； 4，待评价。 ", dataType = "int",
					required = false, defaultValue = "0", allowableValues = "0,1,2,3,4",
					example = "0"),
			@ApiImplicitParam(name = "page", value = "分页", dataType = "int",
					required = false, defaultValue = "1", example = "1"),
			@ApiImplicitParam(name = "limit", value = "单页条数", dataType = "int",
					required = false, defaultValue = "10", example = "10") })
	@GetMapping("list")
	public ResultJson<Page<OrderVo>> list(@LoginUser Integer userId,
			@RequestParam(defaultValue = "0") Integer showType,
			@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer limit,
			@Sort @RequestParam(defaultValue = "add_time") String sort,
			@Order @RequestParam(defaultValue = "desc") String order) {
		return orderManager.list(userId, showType, page, limit, sort, order);
	}

	/**
	 * 订单详情
	 * @param userId 用户ID
	 * @param orderId 订单ID
	 * @return 订单详情
	 */
	@ApiOperation("订单详情")
	@GetMapping("detail")
	public ResultJson<OrderDetailVo> detail(@LoginUser Integer userId,
			@NotNull Integer orderId) {
		return orderManager.detail(userId, orderId);
	}

	/**
	 * 提交订单
	 * @param userId 用户ID
	 * @param body 订单信息，{ cartId：xxx, addressId: xxx, couponId: xxx, message: xxx,
	 * grouponRulesId: xxx, grouponLinkId: xxx}
	 * @return 提交订单操作结果
	 */
	@ApiOperation("订单详情")
	@ApiImplicitParam("订单信息，{ cartId：xxx, addressId: xxx, couponId: xxx, message: xxx,\n"
			+ "     *               grouponRulesId: xxx, grouponLinkId: xxx}")
	@PostMapping("submit")
	public Object submit(@LoginUser Integer userId, @RequestBody String body) {
		return orderManager.submit(userId, body);
	}

	/**
	 * 取消订单
	 * @param userId 用户ID
	 * @param body 订单信息，{ orderId：xxx }
	 * @return 取消订单操作结果
	 */
	@ApiOperation("取消订单")
	@ApiImplicitParam("订单信息 订单信息，{ orderId：xxx }")
	@PostMapping("cancel")
	public Object cancel(@LoginUser Integer userId, @RequestBody String body) {
		return orderManager.cancel(userId, body);
	}

	/**
	 * 付款订单的预支付会话标识
	 * @param userId 用户ID
	 * @param body 订单信息，{ orderId：xxx }
	 * @return 支付订单ID
	 */
	@ApiOperation("付款订单的预支付会话标识")
	@ApiImplicitParam("订单信息，{ orderId：xxx }")
	@PostMapping("prepay")
	public Object prepay(@LoginUser Integer userId, @RequestBody String body,
			HttpServletRequest request) {

		// TODO: 待统一支付逻辑的时候统一处理
		return orderManager.prepay(userId, body, request);
	}

	/**
	 * 微信H5支付
	 * @param userId
	 * @param body
	 * @param request
	 * @return
	 */
	@PostMapping("h5pay")
	public Object h5pay(@LoginUser Integer userId, @RequestBody String body,
			HttpServletRequest request) {
		return orderManager.h5pay(userId, body, request);
	}

	/**
	 * 微信付款成功或失败回调接口
	 * <p>
	 * TODO 注意，这里pay-notify是示例地址，建议开发者应该设立一个隐蔽的回调地址
	 * @param request 请求内容
	 * @param response 响应内容
	 * @return 操作结果
	 */
	@PostMapping("pay-notify")
	public Object payNotify(HttpServletRequest request, HttpServletResponse response) {
		return orderManager.payNotify(request, response);
	}

	/**
	 * 订单申请退款
	 * @param userId 用户ID
	 * @param body 订单信息，{ orderId：xxx }
	 * @return 订单退款操作结果
	 */
	@PostMapping("refund")
	public Object refund(@LoginUser Integer userId, @RequestBody String body) {
		return orderManager.refund(userId, body);
	}

	/**
	 * 确认收货
	 * @param userId 用户ID
	 * @param body 订单信息，{ orderId：xxx }
	 * @return 订单操作结果
	 */
	@PostMapping("confirm")
	public Object confirm(@LoginUser Integer userId, @RequestBody String body) {
		return orderManager.confirm(userId, body);
	}

	/**
	 * 删除订单
	 * @param userId 用户ID
	 * @param body 订单信息，{ orderId：xxx }
	 * @return 订单操作结果
	 */
	@PostMapping("delete")
	public Object delete(@LoginUser Integer userId, @RequestBody String body) {
		return orderManager.delete(userId, body);
	}

	/**
	 * 待评价订单商品信息
	 * @param userId 用户ID
	 * @param orderId 订单ID
	 * @param goodsId 商品ID
	 * @return 待评价订单商品信息
	 */
	@GetMapping("goods")
	public Object goods(@LoginUser Integer userId, @NotNull Integer orderId,
			@NotNull Integer goodsId) {
		return orderManager.goods(userId, orderId, goodsId);
	}

	/**
	 * 评价订单商品
	 * @param userId 用户ID
	 * @param body 订单信息，{ orderId：xxx }
	 * @return 订单操作结果
	 */
	@PostMapping("comment")
	public Object comment(@LoginUser Integer userId, @RequestBody String body) {
		return orderManager.comment(userId, body);
	}

}
