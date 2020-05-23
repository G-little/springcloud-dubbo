package com.little.g.springcloud.thirdpay.dto;

import com.little.g.springcloud.thirdpay.enums.ThirdPayStatus;

import java.io.Serializable;

public class PayCallbackInfo implements Serializable {

	// 系统订单id
	private String payOrderId;

	// 第三方订单id
	private String outPayOrderId;

	// 实际支付金额
	private Long realFee;

	// 第三方关键标识数据
	private String keyData;

	// 失败码
	private String failCode;

	// 第三方支付订单状态
	private ThirdPayStatus thirdPayStatus;

	// 交易结束时间
	private long payTime;

	// 交易结束时间
	private long finishTime;

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

	public Long getRealFee() {
		return realFee;
	}

	public void setRealFee(Long realFee) {
		this.realFee = realFee;
	}

	public String getKeyData() {
		return keyData;
	}

	public void setKeyData(String keyData) {
		this.keyData = keyData;
	}

	public String getFailCode() {
		return failCode;
	}

	public void setFailCode(String failCode) {
		this.failCode = failCode;
	}

	public ThirdPayStatus getThirdPayStatus() {
		return thirdPayStatus;
	}

	public void setThirdPayStatus(ThirdPayStatus thirdPayStatus) {
		this.thirdPayStatus = thirdPayStatus;
	}

	public long getPayTime() {
		return payTime;
	}

	public void setPayTime(long payTime) {
		this.payTime = payTime;
	}

	public long getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(long finishTime) {
		this.finishTime = finishTime;
	}

	@Override
	public String toString() {
		return "PayCallbackInfo{" + "payOrderId='" + payOrderId + '\''
				+ ", outPayOrderId='" + outPayOrderId + '\'' + ", realFee=" + realFee
				+ ", keyData='" + keyData + '\'' + ", failCode='" + failCode + '\''
				+ ", thirdPayStatus=" + thirdPayStatus + ", payTime=" + payTime
				+ ", finishTime=" + finishTime + '}';
	}

}
