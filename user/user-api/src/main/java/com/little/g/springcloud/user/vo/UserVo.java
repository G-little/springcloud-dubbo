package com.little.g.springcloud.user.vo;

import java.io.Serializable;

public class UserVo implements Serializable {

	private String nickname;

	private String avatar;

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

}
