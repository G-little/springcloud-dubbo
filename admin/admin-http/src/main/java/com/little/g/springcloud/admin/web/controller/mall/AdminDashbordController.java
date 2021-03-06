package com.little.g.springcloud.admin.web.controller.mall;

import com.little.g.springcloud.admin.web.vo.DashboardVo;
import com.little.g.springcloud.common.ResultJson;
import com.little.g.springcloud.common.utils.ResponseUtil;
import com.little.g.springcloud.mall.api.LitemallGoodsProductService;
import com.little.g.springcloud.mall.api.LitemallGoodsService;
import com.little.g.springcloud.mall.api.LitemallOrderService;
import com.little.g.springcloud.user.api.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/dashboard")
@Validated
public class AdminDashbordController {

	private final Log logger = LogFactory.getLog(AdminDashbordController.class);

	@Reference
	private UserService userService;

	@Reference
	private LitemallGoodsService goodsService;

	@Reference
	private LitemallGoodsProductService productService;

	@Reference
	private LitemallOrderService orderService;

	@GetMapping("")
	public ResultJson<DashboardVo> info() {
		long userTotal = userService.count();
		int goodsTotal = goodsService.count();
		int productTotal = productService.count();
		int orderTotal = orderService.count();
		DashboardVo data = new DashboardVo();
		data.setUserTotal(new Long(userTotal).intValue());
		data.setGoodsTotal(goodsTotal);
		data.setProductTotal(productTotal);
		data.setOrderTotal(orderTotal);

		return ResponseUtil.ok(data);
	}

}
