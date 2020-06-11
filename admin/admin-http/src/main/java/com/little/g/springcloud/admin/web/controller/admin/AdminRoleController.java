package com.little.g.springcloud.admin.web.controller.admin;

import com.little.g.springcloud.admin.api.AdminRoleService;
import com.little.g.springcloud.admin.params.AdminRoleParams;
import com.little.g.springcloud.admin.web.form.AdminRoleForm;
import com.little.g.springcloud.common.ResultJson;
import com.little.g.springcloud.common.params.PageQueryParam;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by llg on 2019/5/19.
 */
@RequestMapping("/role")
@RestController
public class AdminRoleController {

    @Reference
    private AdminRoleService adminRoleService;

    @RequestMapping("/list")
    public ResultJson list(@Valid PageQueryParam param) {
        ResultJson r = new ResultJson();
        r.setData(adminRoleService.pageList(param));
        return r;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResultJson add(@Valid AdminRoleForm form) {
        ResultJson r = new ResultJson();

        AdminRoleParams params = new AdminRoleParams();
        BeanUtils.copyProperties(form, params);

        if (params.getId() != null && params.getId() > 0) {
            if (adminRoleService.update(params)) {
                return r;
            }
        } else {
            if (adminRoleService.add(params)) {
                return r;
            }
        }
        r.setC(ResultJson.SYSTEM_UNKNOWN_EXCEPTION);

        return r;
    }

    @RequestMapping(value = "/get")
    public ResultJson get(@RequestParam Integer id) {
        ResultJson r = new ResultJson();
        r.setData(adminRoleService.get(id));
        return r;
    }

    @RequestMapping(value = "/status")
    public ResultJson status(@RequestParam Integer id, @RequestParam Byte status) {
        ResultJson r = new ResultJson();
        AdminRoleParams adminRole = new AdminRoleParams();
        adminRole.setId(id);
        if (adminRoleService.update(adminRole)) {
            return r;
        }
        r.setC(ResultJson.SYSTEM_UNKNOWN_EXCEPTION);
        return r;
    }

    @RequestMapping("/del")
    public ResultJson del(@RequestParam Integer id) {
        ResultJson r = new ResultJson();
        if (adminRoleService.delete(id)) {
            return r;
        }
        r.setC(ResultJson.SYSTEM_UNKNOWN_EXCEPTION);

        return r;
    }

}
