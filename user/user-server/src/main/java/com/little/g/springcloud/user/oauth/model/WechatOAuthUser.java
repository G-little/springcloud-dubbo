package com.little.g.springcloud.user.oauth.model;

import java.util.Arrays;

/**
 * Created by lengligang on 2019/4/16.
 */
public class WechatOAuthUser {

	private String openid;

	private String nickname;

	private Byte sex;

	private String province;

	private String city;

	private String country;

	private String headimgurl;

	private String[] privilege;

	private String unionid;

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Byte getSex() {
		return sex;
	}

	public void setSex(Byte sex) {
		this.sex = sex;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}

	public String[] getPrivilege() {
		return privilege;
	}

	public void setPrivilege(String[] privilege) {
		this.privilege = privilege;
	}

	public String getUnionid() {
		return unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("WechatOAuthUser{");
		sb.append("openid='").append(openid).append('\'');
		sb.append(", nickname='").append(nickname).append('\'');
		sb.append(", sex=").append(sex);
		sb.append(", province='").append(province).append('\'');
		sb.append(", city='").append(city).append('\'');
		sb.append(", country='").append(country).append('\'');
		sb.append(", headimgurl='").append(headimgurl).append('\'');
		sb.append(", privilege=")
				.append(privilege == null ? "null" : Arrays.asList(privilege).toString());
		sb.append(", unionid='").append(unionid).append('\'');
		sb.append('}');
		return sb.toString();
	}

}
