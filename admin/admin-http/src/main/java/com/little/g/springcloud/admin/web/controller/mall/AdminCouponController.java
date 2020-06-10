package com.little.g.springcloud.admin.web.controller.mall;

import com.github.pagehelper.PageInfo;
import com.little.g.springcloud.admin.web.annotation.RequiresPermissions;
import com.little.g.springcloud.admin.web.annotation.RequiresPermissionsDesc;
import com.little.g.springcloud.common.ResultJson;
import com.little.g.springcloud.common.dto.Page;
import com.little.g.springcloud.common.utils.ResponseUtil;
import com.little.g.springcloud.mall.api.LitemallCouponService;
import com.little.g.springcloud.mall.api.LitemallCouponUserService;
import com.little.g.springcloud.mall.dto.LitemallCouponDTO;
import com.little.g.springcloud.mall.dto.LitemallCouponUserDTO;
import com.little.g.springcloud.mall.util.CouponConstant;
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

@Api("优惠券管理")
@RestController
@RequestMapping("/admin/coupon")
@Validated
public class AdminCouponController {

    private final Log logger = LogFactory.getLog(AdminCouponController.class);

    @Reference
    private LitemallCouponService couponService;

    @Reference
    private LitemallCouponUserService couponUserService;

    @ApiOperation("优惠券管理")
    @RequiresPermissions("admin:coupon:list")
    @RequiresPermissionsDesc(menu = {"推广管理", "优惠券管理"}, button = "查询")
    @GetMapping("/list")
    public ResultJson<Page<LitemallCouponDTO>> list(String name, Short type, Short status,
                                                    @RequestParam(defaultValue = "1") Integer page,
                                                    @RequestParam(defaultValue = "10") Integer limit,
                                                    @Sort @RequestParam(defaultValue = "add_time") String sort,
                                                    @Order @RequestParam(defaultValue = "desc") String order) {
        PageInfo<LitemallCouponDTO> pageInfo = couponService.querySelective(name, type,
                status, page, limit, sort, order);
        return ResponseUtil.okPage(pageInfo);
    }

    @ApiOperation("优惠券用户管理")
    @RequiresPermissions("admin:coupon:listuser")
    @RequiresPermissionsDesc(menu = {"推广管理", "优惠券管理"}, button = "查询用户")
    @GetMapping("/listuser")
    public ResultJson<Page<LitemallCouponUserDTO>> listuser(Integer userId, Integer couponId, Short status,
                                                            @RequestParam(defaultValue = "1") Integer page,
                                                            @RequestParam(defaultValue = "10") Integer limit,
                                                            @Sort @RequestParam(defaultValue = "add_time") String sort,
                                                            @Order @RequestParam(defaultValue = "desc") String order) {
        PageInfo<LitemallCouponUserDTO> pageInfo = couponUserService.queryList(userId,
                couponId, status, page, limit, sort, order);
        return ResponseUtil.okPage(pageInfo);
    }

    private ResultJson validate(LitemallCouponDTO coupon) {
        String name = coupon.getName();
        if (StringUtils.isEmpty(name)) {
            return ResponseUtil.badArgument();
        }
        return null;
    }

    @ApiOperation("添加优惠券")
    @RequiresPermissions("admin:coupon:create")
    @RequiresPermissionsDesc(menu = {"推广管理", "优惠券管理"}, button = "添加")
    @PostMapping("/create")
    public ResultJson create(@RequestBody LitemallCouponDTO coupon) {
        ResultJson error = validate(coupon);
        if (error != null) {
            return error;
        }

        // 如果是兑换码类型，则这里需要生存一个兑换码
        if (coupon.getType().equals(CouponConstant.TYPE_CODE)) {
            String code = couponService.generateCode();
            coupon.setCode(code);
        }

        couponService.add(coupon);
        return ResponseUtil.ok(coupon);
    }

    @ApiOperation("优惠券详情")
    @RequiresPermissions("admin:coupon:read")
    @RequiresPermissionsDesc(menu = {"推广管理", "优惠券管理"}, button = "详情")
    @GetMapping("/read")
    public ResultJson<LitemallCouponDTO> read(@NotNull Integer id) {
        LitemallCouponDTO coupon = couponService.findById(id);
        return ResponseUtil.ok(coupon);
    }

    @ApiOperation("优惠券编辑")
    @RequiresPermissions("admin:coupon:update")
    @RequiresPermissionsDesc(menu = {"推广管理", "优惠券管理"}, button = "编辑")
    @PostMapping("/update")
    public ResultJson update(@RequestBody LitemallCouponDTO coupon) {
        ResultJson error = validate(coupon);
        if (error != null) {
            return error;
        }
        if (couponService.updateById(coupon) == 0) {
            return ResponseUtil.updatedDataFailed();
        }
        return ResponseUtil.ok(coupon);
    }

    @ApiOperation("删除优惠券")
    @RequiresPermissions("admin:coupon:delete")
    @RequiresPermissionsDesc(menu = {"推广管理", "优惠券管理"}, button = "删除")
    @PostMapping("/delete")
    public ResultJson delete(@RequestBody LitemallCouponDTO coupon) {
        couponService.deleteById(coupon.getId());
        return ResponseUtil.ok();
    }

}
