package com.little.g.springcloud.admin.web.form;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by llg on 2019/10/20.
 */
public class LoginForm {

	@NotBlank
	@Length(min = 11, max = 11)
	private String mobile;

	@NotBlank
	@Length(min = 6, max = 30)
	private String password;

	@Length(min = 4, max = 6)
	@NotBlank
	private String smscode;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSmscode() {
		return smscode;
	}

	public void setSmscode(String smscode) {
		this.smscode = smscode;
	}

}
