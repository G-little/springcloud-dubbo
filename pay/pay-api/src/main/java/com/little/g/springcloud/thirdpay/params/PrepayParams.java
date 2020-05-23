package com.little.g.springcloud.thirdpay.params;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class PrepayParams implements Serializable {

	/**
	 * 关联三方流水
	 */
	@NotEmpty
	private String tradeno;

	/**
	 * 交易金额
	 */
	@NotNull
	@Min(1)
	private Long money;

	/**
	 * 交易描述
	 */
	@NotNull
	@NotEmpty
	private String comment;

	private String openid;

	private String userIp;

	public String getTradeno() {
		return tradeno;
	}

	public void setTradeno(String tradeno) {
		this.tradeno = tradeno;
	}

	public Long getMoney() {
		return money;
	}

	public void setMoney(Long money) {
		this.money = money;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	@Override
	public String toString() {
		return "PrepayParams{" + "tradeno='" + tradeno + '\'' + ", money=" + money
				+ ", comment='" + comment + '\'' + ", openid='" + openid + '\'' + '}';
	}

	public String getUserIp() {
		return userIp;
	}

	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}

}
