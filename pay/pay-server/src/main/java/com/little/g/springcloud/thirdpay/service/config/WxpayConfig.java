package com.little.g.springcloud.thirdpay.service.config;

import javax.validation.constraints.NotEmpty;

public class WxpayConfig {

	/**
	 * appId
	 */
	@NotEmpty
	private String appId;

	/**
	 * 商户号
	 */
	@NotEmpty
	private String mchId;

	/**
	 * 签名key
	 */
	@NotEmpty
	private String mchKey;

	/**
	 * 证书路径
	 */
	private String keyPath;

	/**
	 * JSAPI--公众号支付 NATIVE--原生扫码支付 APP--app支付
	 */
	@NotEmpty
	private String tradeType;

	/**
	 * 退款回调
	 */
	private String refundUrl;

	@NotEmpty
	private String notifyUrl;

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getMchId() {
		return mchId;
	}

	public void setMchId(String mchId) {
		this.mchId = mchId;
	}

	public String getMchKey() {
		return mchKey;
	}

	public void setMchKey(String mchKey) {
		this.mchKey = mchKey;
	}

	public String getKeyPath() {
		return keyPath;
	}

	public void setKeyPath(String keyPath) {
		this.keyPath = keyPath;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public String getRefundUrl() {
		return refundUrl;
	}

	public void setRefundUrl(String refundUrl) {
		this.refundUrl = refundUrl;
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	@Override
	public String toString() {
		return "WxpayConfig{" + "appId='" + appId + '\'' + ", mchId='" + mchId + '\''
				+ ", mchKey='" + mchKey + '\'' + ", keyPath='" + keyPath + '\''
				+ ", tradeType='" + tradeType + '\'' + ", refundUrl='" + refundUrl + '\''
				+ ", notifyUrl='" + notifyUrl + '\'' + '}';
	}

}
