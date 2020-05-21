package com.little.g.springcloud.user.model;

import java.io.Serializable;

public class OAuthUser extends OAuthUserKey implements Serializable {

	private Long uid;

	private String accessToken;

	private Integer expiresIn;

	private String refreshToken;

	private Integer reExpiresIn;

	private String name;

	private String avatar;

	private Byte gender;

	private Long updateTime;

	private String unionid;

	private static final long serialVersionUID = 1L;

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken == null ? null : accessToken.trim();
	}

	public Integer getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(Integer expiresIn) {
		this.expiresIn = expiresIn;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken == null ? null : refreshToken.trim();
	}

	public Integer getReExpiresIn() {
		return reExpiresIn;
	}

	public void setReExpiresIn(Integer reExpiresIn) {
		this.reExpiresIn = reExpiresIn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar == null ? null : avatar.trim();
	}

	public Byte getGender() {
		return gender;
	}

	public void setGender(Byte gender) {
		this.gender = gender;
	}

	public Long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}

	public String getUnionid() {
		return unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid == null ? null : unionid.trim();
	}

}
