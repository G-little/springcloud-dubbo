package com.little.g.springcloud.admin.dto;

import java.io.Serializable;

public class ResourcesDTO implements Serializable {

	private Integer id;

	private String title;

	private String path;

	private Integer parentId;

	private Byte type;

	private Byte isMenu;

	private Long createTime;

	private Integer privilegePos;

	private Long privilegeVal;

	private Byte needAuth;

	private static final long serialVersionUID = 1L;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title == null ? null : title.trim();
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path == null ? null : path.trim();
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Byte getType() {
		return type;
	}

	public void setType(Byte type) {
		this.type = type;
	}

	public Byte getIsMenu() {
		return isMenu;
	}

	public void setIsMenu(Byte isMenu) {
		this.isMenu = isMenu;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Integer getPrivilegePos() {
		return privilegePos;
	}

	public void setPrivilegePos(Integer privilegePos) {
		this.privilegePos = privilegePos;
	}

	public Long getPrivilegeVal() {
		return privilegeVal;
	}

	public void setPrivilegeVal(Long privilegeVal) {
		this.privilegeVal = privilegeVal;
	}

	public Byte getNeedAuth() {
		return needAuth;
	}

	public void setNeedAuth(Byte needAuth) {
		this.needAuth = needAuth;
	}

}