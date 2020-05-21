package com.little.g.springcloud.user.model;

import java.io.Serializable;

public class OAuthUserKey implements Serializable {

	private String oauthType;

	private String openid;

	private static final long serialVersionUID = 1L;

	public String getOauthType() {
		return oauthType;
	}

	public void setOauthType(String oauthType) {
		this.oauthType = oauthType == null ? null : oauthType.trim();
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid == null ? null : openid.trim();
	}

}
