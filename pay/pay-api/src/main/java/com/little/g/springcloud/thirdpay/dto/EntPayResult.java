package com.little.g.springcloud.thirdpay.dto;

import java.io.Serializable;

public class EntPayResult implements Serializable {

	/**
	 * 交易流水
	 */
	private String tradeNo;

	/**
	 * 三方交易流水
	 */
	private String outTradeNo;

	/**
	 * 支付成功时间
	 */
	private String paymentTime;

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public String getPaymentTime() {
		return paymentTime;
	}

	public void setPaymentTime(String paymentTime) {
		this.paymentTime = paymentTime;
	}

	@Override
	public String toString() {
		return "EntPayResult{" + "tradeNo='" + tradeNo + '\'' + ", outTradeNo='"
				+ outTradeNo + '\'' + ", paymentTime='" + paymentTime + '\'' + '}';
	}

}
