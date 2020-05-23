package com.little.g.springcloud.admin.params;

import com.little.g.springcloud.common.params.PageQueryParam;

public class AdminUserSearchParam extends PageQueryParam {

	private String realName;

	/**
	 * 归属
	 */
	private Byte belongTo;

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Byte getBelongTo() {
		return belongTo;
	}

	public void setBelongTo(Byte belongTo) {
		this.belongTo = belongTo;
	}

}
