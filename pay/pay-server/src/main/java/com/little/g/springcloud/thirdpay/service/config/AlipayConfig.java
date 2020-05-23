package com.little.g.springcloud.thirdpay.service.config;

import javax.validation.constraints.NotEmpty;

public class AlipayConfig {

	@NotEmpty
	private String appid;

	@NotEmpty
	private String privatekey;

	@NotEmpty
	private String publickey;

	@NotEmpty
	private String notifyurl;

	@NotEmpty
	private String appSubject;

	private String timeout = "30m";

	@NotEmpty
	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getPrivatekey() {
		return privatekey;
	}

	public void setPrivatekey(String privatekey) {
		this.privatekey = privatekey;
	}

	public String getPublickey() {
		return publickey;
	}

	public void setPublickey(String publickey) {
		this.publickey = publickey;
	}

	public String getNotifyurl() {
		return notifyurl;
	}

	public void setNotifyurl(String notifyurl) {
		this.notifyurl = notifyurl;
	}

	public String getAppSubject() {
		return appSubject;
	}

	public String getTimeout() {
		return timeout;
	}

}
