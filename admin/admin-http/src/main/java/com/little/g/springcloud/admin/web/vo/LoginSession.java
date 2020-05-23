package com.little.g.springcloud.admin.web.vo;

import com.little.g.springcloud.admin.dto.AdminUserDTO;

import java.io.Serializable;

/**
 * Created by llg on 2019/10/20.
 */
public class LoginSession implements Serializable {

	private AdminUserDTO adminUser;

	public AdminUserDTO getAdminUser() {
		return adminUser;
	}

	public void setAdminUser(AdminUserDTO adminUser) {
		this.adminUser = adminUser;
	}

}
