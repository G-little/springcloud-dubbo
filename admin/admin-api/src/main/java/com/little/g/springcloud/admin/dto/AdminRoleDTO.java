package com.little.g.springcloud.admin.dto;

import java.io.Serializable;

public class AdminRoleDTO implements Serializable {

	private Integer id;

	private String name;

	private Long[] privilege;

	private Long createTime;

	private static final long serialVersionUID = 1L;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public Long[] getPrivilege() {
		return privilege;
	}

	public void setPrivilege(Long[] privilege) {
		this.privilege = privilege;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

}
