package com.little.g.springcloud.admin.web.controller.mall;

import com.github.pagehelper.PageInfo;
import com.little.g.springcloud.admin.web.annotation.RequiresPermissions;
import com.little.g.springcloud.admin.web.annotation.RequiresPermissionsDesc;
import com.little.g.springcloud.common.utils.ResponseUtil;
import com.little.g.springcloud.mall.api.LitemallAddressService;
import com.little.g.springcloud.mall.api.LitemallRegionService;
import com.little.g.springcloud.mall.dto.LitemallAddressDTO;
import com.little.g.springcloud.mall.validator.Order;
import com.little.g.springcloud.mall.validator.Sort;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/address")
@Validated
public class AdminAddressController {

	private final Log logger = LogFactory.getLog(AdminAddressController.class);

	@Reference
	private LitemallAddressService addressService;

	@Reference
	private LitemallRegionService regionService;

	@RequiresPermissions("admin:address:list")
	@RequiresPermissionsDesc(menu = { "用户管理", "收货地址" }, button = "查询")
	@GetMapping("/list")
	public Object list(Integer userId, String name,
			@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer limit,
			@Sort @RequestParam(defaultValue = "add_time") String sort,
			@Order @RequestParam(defaultValue = "desc") String order) {

		PageInfo<LitemallAddressDTO> pageInfo = addressService.querySelective(userId,
				name, page, limit, sort, order);
		return ResponseUtil.okPage(pageInfo);
	}

}
