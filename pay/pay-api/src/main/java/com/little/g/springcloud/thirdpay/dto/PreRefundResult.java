package com.little.g.springcloud.thirdpay.dto;

import java.io.Serializable;

/**
 * 退款结果
 *
 */
public class PreRefundResult implements Serializable {

	// 退款id
	private String refundId;

	// 外部退款id
	private String outRefundId;

	// 是否成功处理
	private boolean isSuccessOp;

	// 错误码
	private String errorCode;

	// 错误信息
	private String errorMsg;

	/**
	 * 退款结果
	 */
	RefundResult refundResult;

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

	public boolean isSuccessOp() {
		return isSuccessOp;
	}

	public void setSuccessOp(boolean successOp) {
		isSuccessOp = successOp;
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

	public RefundResult getRefundResult() {
		return refundResult;
	}

	public void setRefundResult(RefundResult refundResult) {
		this.refundResult = refundResult;
	}

}
