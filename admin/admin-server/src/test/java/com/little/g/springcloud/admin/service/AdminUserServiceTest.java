package com.little.g.springcloud.admin.service;

import com.little.g.springcloud.admin.api.AdminUserService;
import com.little.g.springcloud.admin.dto.AdminUserDTO;
import org.junit.Test;

import javax.annotation.Resource;

public class AdminUserServiceTest extends BaseTest {

	@Resource
	private AdminUserService adminUserService;

	@Test
	public void testLogin() {
		AdminUserDTO login = adminUserService.login("15201008961", "123456", "639692");
		System.out.println(login);
	}

}
