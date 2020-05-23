package com.little.g.springcloud.thirdpay.dto;

import java.io.Serializable;

/**
 * 下单结果
 */
public class PrePayResult implements Serializable {

	public PrePayResult(String payOrderId, String callPayInfo) {
		this.payOrderId = payOrderId;
		this.callPayInfo = callPayInfo;
	}

	public PrePayResult(String payOrderId, String callPayInfo, String outPayOrderId) {
		this.payOrderId = payOrderId;
		this.callPayInfo = callPayInfo;
		this.outPayOrderId = outPayOrderId;
	}

	// 支付订单id
	private String payOrderId;

	// 第三方支付订单id
	private String outPayOrderId;

	// 支付调起信息
	private String callPayInfo;

	// 回调信息
	private PayCallbackInfo payCallbackInfo;

	public String getPayOrderId() {
		return payOrderId;
	}

	public void setPayOrderId(String payOrderId) {
		this.payOrderId = payOrderId;
	}

	public String getOutPayOrderId() {
		return outPayOrderId;
	}

	public void setOutPayOrderId(String outPayOrderId) {
		this.outPayOrderId = outPayOrderId;
	}

	public PayCallbackInfo getPayCallbackInfo() {
		return payCallbackInfo;
	}

	public void setPayCallbackInfo(PayCallbackInfo payCallbackInfo) {
		this.payCallbackInfo = payCallbackInfo;
	}

	public String getCallPayInfo() {
		return callPayInfo;
	}

	public void setCallPayInfo(String callPayInfo) {
		this.callPayInfo = callPayInfo;
	}

	@Override
	public String toString() {
		return "PrePayResult{" + "payOrderId='" + payOrderId + '\'' + ", outPayOrderId='"
				+ outPayOrderId + '\'' + ", callPayInfo='" + callPayInfo + '\''
				+ ", payCallbackInfo=" + payCallbackInfo + '}';
	}

}
