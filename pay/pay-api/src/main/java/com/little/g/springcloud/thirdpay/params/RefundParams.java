package com.little.g.springcloud.thirdpay.params;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 退款参数
 *
 */
public class RefundParams implements Serializable {

	// 系统订单id
	@NotEmpty
	private String payOrderId;

	// 外部支付订单id
	@NotEmpty
	private String outPayOrderId;

	// 退款单号
	private String refundId;

	// 原支付总金额
	private Long payTotalFee;

	// 退款总金额
	@NotNull
	@Min(1)
	private Long refundFee;

	// 退款原因
	@NotEmpty
	private String refundReason;

	// 用户ID
	private long userId;

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

	public String getRefundId() {
		return refundId;
	}

	public void setRefundId(String refundId) {
		this.refundId = refundId;
	}

	public Long getPayTotalFee() {
		return payTotalFee;
	}

	public void setPayTotalFee(Long payTotalFee) {
		this.payTotalFee = payTotalFee;
	}

	public Long getRefundFee() {
		return refundFee;
	}

	public void setRefundFee(Long refundFee) {
		this.refundFee = refundFee;
	}

	public String getRefundReason() {
		return refundReason;
	}

	public void setRefundReason(String refundReason) {
		this.refundReason = refundReason;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "RefundParams{" + "payOrderId='" + payOrderId + '\'' + ", outPayOrderId='"
				+ outPayOrderId + '\'' + ", refundId='" + refundId + '\''
				+ ", payTotalFee=" + payTotalFee + ", refundFee=" + refundFee
				+ ", refundReason='" + refundReason + '\'' + ", userId=" + userId + '}';
	}

}
