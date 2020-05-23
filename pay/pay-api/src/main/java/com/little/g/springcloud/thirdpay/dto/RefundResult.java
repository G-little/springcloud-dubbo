package com.little.g.springcloud.thirdpay.dto;

import com.little.g.springcloud.thirdpay.enums.ThirdRefundStatus;

import java.io.Serializable;

/**
 * 退款结果
 *
 */
public class RefundResult implements Serializable {

	// 系统退款id
	private String refundId;

	// 第三方退款id
	private String outRefundId;

	// 第三方退款状态
	private ThirdRefundStatus thirdRefundStatus;

	// 第三方错误码
	private String errorCode;

	// 退款金额
	private Long refundFee;

	// 错误信息
	private String errorMsg;

	public String getRefundId() {
		return refundId;
	}

	public void setRefundId(String refundId) {
		this.refundId = refundId;
	}

	public String getOutRefundId() {
		return outRefundId;
	}

	public void setOutRefundId(String outRefundId) {
		this.outRefundId = outRefundId;
	}

	public ThirdRefundStatus getThirdRefundStatus() {
		return thirdRefundStatus;
	}

	public void setThirdRefundStatus(ThirdRefundStatus thirdRefundStatus) {
		this.thirdRefundStatus = thirdRefundStatus;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public Long getRefundFee() {
		return refundFee;
	}

	public void setRefundFee(Long refundFee) {
		this.refundFee = refundFee;
	}

	@Override
	public String toString() {
		return "RefundResult{" + "refundId='" + refundId + '\'' + ", outRefundId='"
				+ outRefundId + '\'' + ", thirdRefundStatus=" + thirdRefundStatus
				+ ", errorCode='" + errorCode + '\'' + ", refundFee=" + refundFee + '}';
	}

}
