package com.little.g.springcloud.admin.web.form;

import java.io.Serializable;

public class AdminRoleForm implements Serializable {

	private Integer id;

	private String name;

	private Integer[] resources;

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

	public Integer[] getResources() {
		return resources;
	}

	public void setResources(Integer[] resources) {
		this.resources = resources;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

}
