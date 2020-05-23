package com.little.g.springcloud.admin.web.controller;

import com.little.g.springcloud.admin.api.AdminUserService;
import com.little.g.springcloud.admin.params.AdminUserParams;
import com.little.g.springcloud.admin.params.AdminUserSearchParam;
import com.little.g.springcloud.admin.web.form.AdminUserForm;
import com.little.g.springcloud.common.ResultJson;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.text.ParseException;

/**
 * Created by llg on 2019/10/20.
 */
@RestController
@RequestMapping("/admin")
public class AdminUserController {

	@Reference
	private AdminUserService adminUserService;

	@RequestMapping("/list")
	public ResultJson list(@Valid AdminUserSearchParam param) {
		ResultJson r = new ResultJson();
		r.setData(adminUserService.pageList(param));
		return r;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResultJson add(@Valid AdminUserForm adminUser) throws ParseException {
		ResultJson r = new ResultJson();
		AdminUserParams params = new AdminUserParams();
		BeanUtils.copyProperties(adminUser, params);
		if (!StringUtils.isEmpty(adminUser.getBirthday())) {
			params.setBirthday(
					DateUtils.parseDate(adminUser.getBirthday(), "yyyy-MM-dd").getTime());
		}
		if (adminUser.getId() != null && adminUser.getId() > 0) {
			if (adminUserService.update(params)) {
				return r;
			}
		}
		else {
			if (adminUserService.add(params)) {
				return r;
			}
		}
		r.setC(ResultJson.SYSTEM_UNKNOWN_EXCEPTION);

		return r;
	}

	@RequestMapping(value = "/get")
	public ResultJson get(@RequestParam Integer id) {
		ResultJson r = new ResultJson();
		r.setData(adminUserService.get(id));
		return r;
	}

	@RequestMapping(value = "/status")
	public ResultJson status(@RequestParam Integer id, @RequestParam Byte status) {
		ResultJson r = new ResultJson();
		AdminUserParams adminUser = new AdminUserParams();
		adminUser.setId(id);
		if (adminUserService.update(adminUser)) {
			return r;
		}
		r.setC(ResultJson.SYSTEM_UNKNOWN_EXCEPTION);
		return r;
	}

	@RequestMapping("/del")
	public ResultJson del(@RequestParam Integer id) {
		ResultJson r = new ResultJson();
		if (adminUserService.delete(id)) {
			return r;
		}
		r.setC(ResultJson.SYSTEM_UNKNOWN_EXCEPTION);

		return r;
	}

}
