package com.little.g.springcloud.admin.web.controller.mall;

import com.little.g.springcloud.admin.web.annotation.RequiresPermissions;
import com.little.g.springcloud.admin.web.annotation.RequiresPermissionsDesc;
import com.little.g.springcloud.common.utils.ResponseUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/index")
public class AdminIndexController {

	private final Log logger = LogFactory.getLog(AdminIndexController.class);

	@RequestMapping("/index")
	public Object index() {
		return ResponseUtil.ok("hello world, this is admin service");
	}

	@RequestMapping("/guest")
	public Object guest() {
		return ResponseUtil.ok("hello world, this is admin service");
	}

	@RequestMapping("/authn")
	public Object authn() {
		return ResponseUtil.ok("hello world, this is admin service");
	}

	@RequestMapping("/user")
	public Object user() {
		return ResponseUtil.ok("hello world, this is admin service");
	}

	@RequestMapping("/admin")
	public Object admin() {
		return ResponseUtil.ok("hello world, this is admin service");
	}

	@RequestMapping("/admin2")
	public Object admin2() {
		return ResponseUtil.ok("hello world, this is admin service");
	}

	@RequiresPermissions("index:permission:read")
	@RequiresPermissionsDesc(menu = { "其他", "权限测试" }, button = "权限读")
	@GetMapping("/read")
	public Object read() {
		return ResponseUtil.ok("hello world, this is admin service");
	}

	@RequiresPermissions("index:permission:write")
	@RequiresPermissionsDesc(menu = { "其他", "权限测试" }, button = "权限写")
	@PostMapping("/write")
	public Object write() {
		return ResponseUtil.ok("hello world, this is admin service");
	}

}
