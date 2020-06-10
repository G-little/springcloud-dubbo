package com.little.g.springcloud.mall.web.controller;

import com.little.g.springcloud.common.ResultJson;
import com.little.g.springcloud.common.utils.ResponseUtil;
import com.little.g.springcloud.common.web.annotation.LoginUser;
import com.little.g.springcloud.mall.api.LitemallOrderService;
import com.little.g.springcloud.mall.web.vo.UserIndexVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户服务
 */
@Api("用户服务")
@RestController
@RequestMapping("/user")
@Validated
@Slf4j
public class UserController {

    @Reference
    private LitemallOrderService orderService;

    /**
     * 用户个人页面数据
     * <p>
     * 目前是用户订单统计信息
     *
     * @param userId 用户ID
     * @return 用户个人页面数据
     */
    @ApiOperation("用户个人页面数据")
    @GetMapping("index")
    public ResultJson<UserIndexVo> list(@LoginUser Integer userId) {
        if (userId == null) {
            return ResponseUtil.unlogin();
        }

        UserIndexVo data = new UserIndexVo();
        data.setOrder(orderService.orderInfo(userId));
        return ResponseUtil.ok(data);
    }

}
