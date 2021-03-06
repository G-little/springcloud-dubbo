package com.little.g.springcloud.mall.web.controller;

import com.little.g.springcloud.common.ResultJson;
import com.little.g.springcloud.common.dto.Page;
import com.little.g.springcloud.common.utils.RegexUtil;
import com.little.g.springcloud.common.utils.ResponseUtil;
import com.little.g.springcloud.common.web.annotation.LoginUser;
import com.little.g.springcloud.mall.api.LitemallAddressService;
import com.little.g.springcloud.mall.api.LitemallRegionService;
import com.little.g.springcloud.mall.dto.LitemallAddressDTO;
import com.little.g.springcloud.mall.web.manager.GetRegionManager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 用户收货地址服务
 */

@Api("用户收货地址相关接口")
@RestController
@RequestMapping("/address")
@Slf4j
@Validated
public class AddressController extends GetRegionManager {

	@Reference
	private LitemallAddressService addressService;

	@Reference
	private LitemallRegionService regionService;

	/**
	 * 用户收货地址列表
	 * @param userId 用户ID
	 * @return 收货地址列表
	 */

	@ApiOperation(value = "拉取收货地址列表", notes = "获取用户填写的地理位置信息历史记录")
	@GetMapping("list")
	public ResultJson<Page<LitemallAddressDTO>> list(@LoginUser Integer userId) {

		List<LitemallAddressDTO> addressList = addressService.queryByUid(userId);
		return ResponseUtil.okList(addressList);
	}

	/**
	 * 收货地址详情
	 * @param userId 用户ID
	 * @param id 收货地址ID
	 * @return 收货地址详情
	 */
	@ApiOperation(value = "获取收货地址详细信息", notes = "获取收货地址详细信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "地址id", required = true) })
	@GetMapping("detail")
	public ResultJson<LitemallAddressDTO> detail(@LoginUser Integer userId,
			@RequestParam("id") @NotNull Integer id) {

		LitemallAddressDTO address = addressService.query(userId, id);
		if (address == null) {
			return ResponseUtil.badArgumentValue();
		}
		return ResponseUtil.ok(address);
	}

	private ResultJson validate(LitemallAddressDTO address) {
		String name = address.getName();
		if (StringUtils.isEmpty(name)) {
			return ResponseUtil.badArgument();
		}

		// 测试收货手机号码是否正确
		String mobile = address.getTel();
		if (StringUtils.isEmpty(mobile)) {
			return ResponseUtil.badArgument();
		}
		if (!RegexUtil.isMobileExact(mobile)) {
			return ResponseUtil.badArgument();
		}

		String province = address.getProvince();
		if (StringUtils.isEmpty(province)) {
			return ResponseUtil.badArgument();
		}

		String city = address.getCity();
		if (StringUtils.isEmpty(city)) {
			return ResponseUtil.badArgument();
		}

		String county = address.getCounty();
		if (StringUtils.isEmpty(county)) {
			return ResponseUtil.badArgument();
		}

		String areaCode = address.getAreaCode();
		if (StringUtils.isEmpty(areaCode)) {
			return ResponseUtil.badArgument();
		}

		String detailedAddress = address.getAddressDetail();
		if (StringUtils.isEmpty(detailedAddress)) {
			return ResponseUtil.badArgument();
		}

		Boolean isDefault = address.getDefault();
		if (isDefault == null) {
			return ResponseUtil.badArgument();
		}
		return null;
	}

	/**
	 * 添加或更新收货地址
	 * @param userId 用户ID
	 * @param address 用户收货地址
	 * @return 添加或更新操作结果
	 */
	@PostMapping("save")
	public ResultJson save(@LoginUser Integer userId,
			@RequestBody LitemallAddressDTO address) {
		if (userId == null) {
			return ResponseUtil.unlogin();
		}
		ResultJson error = validate(address);
		if (error != null) {
			return error;
		}

		if (address.getId() == null || address.getId().equals(0)) {
			if (address.getDefault()) {
				// 重置其他收货地址的默认选项
				addressService.resetDefault(userId);
			}

			address.setId(null);
			address.setUserId(userId);
			addressService.add(address);
		}
		else {
			LitemallAddressDTO litemallAddress = addressService.query(userId,
					address.getId());
			if (litemallAddress == null) {
				return ResponseUtil.badArgumentValue();
			}

			if (address.getDefault()) {
				// 重置其他收货地址的默认选项
				addressService.resetDefault(userId);
			}

			address.setUserId(userId);
			addressService.update(address);
		}
		return ResponseUtil.ok(address.getId());
	}

	/**
	 * 删除收货地址
	 * @param userId 用户ID
	 * @param id 用户收货地址，{ id: xxx }
	 * @return 删除操作结果
	 */
	@ApiOperation(value = "删除收货地址", notes = "根据id删除收货地址")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", dataType = "int",
			defaultValue = "1", example = "1", value = "地址id", required = true) })
	@PostMapping("delete")
	public ResultJson delete(@LoginUser Integer userId, @RequestParam Integer id) {
		if (userId == null) {
			return ResponseUtil.unlogin();
		}
		LitemallAddressDTO litemallAddress = addressService.query(userId, id);
		if (litemallAddress == null) {
			return ResponseUtil.badArgumentValue();
		}

		addressService.delete(id);
		return ResponseUtil.ok();
	}

}
