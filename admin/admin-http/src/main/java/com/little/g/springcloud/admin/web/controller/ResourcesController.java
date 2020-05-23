package com.little.g.springcloud.admin.web.controller;

import com.little.g.springcloud.admin.api.ResourcesService;
import com.little.g.springcloud.admin.dto.ResourcesDTO;
import com.little.g.springcloud.admin.web.utils.SessionUtils;
import com.little.g.springcloud.common.ResultJson;
import com.little.g.springcloud.common.params.PageQueryParam;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by llg on 2019/10/20.
 */
@RestController
@RequestMapping("/resources")
public class ResourcesController {

	@Reference
	private ResourcesService resourcesService;

	@RequestMapping("/list")
	public ResultJson list(@Valid PageQueryParam param) {
		ResultJson r = new ResultJson();
		r.setData(resourcesService.pageList(param));
		return r;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResultJson add(@Valid ResourcesDTO resources) {
		ResultJson r = new ResultJson();
		if (resources.getId() != null && resources.getId() > 0) {
			if (resourcesService.update(resources)) {
				return r;
			}
		}
		else {
			if (resourcesService.add(resources)) {
				return r;
			}
		}
		r.setC(ResultJson.SYSTEM_UNKNOWN_EXCEPTION);

		return r;
	}

	@RequestMapping(value = "/get")
	public ResultJson get(@RequestParam Integer id) {
		ResultJson r = new ResultJson();
		r.setData(resourcesService.get(id));
		return r;
	}

	@RequestMapping(value = "/status")
	public ResultJson status(@RequestParam Integer id, @RequestParam Byte status) {
		ResultJson r = new ResultJson();
		ResourcesDTO resources = new ResourcesDTO();
		resources.setId(id);
		if (resourcesService.update(resources)) {
			return r;
		}
		r.setC(ResultJson.SYSTEM_UNKNOWN_EXCEPTION);
		return r;
	}

	@RequestMapping("/del")
	public ResultJson del(@RequestParam Integer id) {
		ResultJson r = new ResultJson();
		if (resourcesService.delete(id)) {
			return r;
		}
		r.setC(ResultJson.SYSTEM_UNKNOWN_EXCEPTION);

		return r;
	}

	@RequestMapping("/menus")
	public ResultJson menus(Integer adminId, HttpServletRequest request) {
		if (adminId == null) {
			adminId = SessionUtils.getAdminId(request);
		}

		ResultJson result = new ResultJson();

		result.setData(resourcesService.userResources(adminId));

		return result;
	}

}
