package com.little.g.springcloud.user.web.controller;

import com.little.g.springcloud.common.ResultJson;
import com.little.g.springcloud.user.api.OAuth2Service;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by lengligang on 2019/4/17.
 */
@RequestMapping("/oauth2")
@RestController
public class OAuth20Controller {
    @Resource
    private OAuth2Service oauth2Service;

    @RequestMapping(value = "/{type}/callback", method= RequestMethod.GET)
    public ResultJson callback(@RequestParam(value = "code", required = true) String code,
                               @PathVariable(value = "type") String type,
                               String deviceId, Byte deviceType){
        return oauth2Service.login(type, code,deviceId, deviceType);
    }
}
