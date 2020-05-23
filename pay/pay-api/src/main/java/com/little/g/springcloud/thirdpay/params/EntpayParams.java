package com.little.g.springcloud.thirdpay.params;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 企业付款到个人
 */
public class EntpayParams implements Serializable {

	/**
	 * 商户订单号
	 */
	@NotEmpty
	private String tradeNo;

	/**
	 * 转账账号
	 */
	@NotEmpty
	private String openid;

	/**
	 * 转账金额
	 */
	@NotNull
	@Min(1)
	private Long amount;

	/**
	 * 转账说明
	 */
	@NotEmpty
	private String comment;

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
