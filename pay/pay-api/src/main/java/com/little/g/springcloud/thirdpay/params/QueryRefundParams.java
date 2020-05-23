package com.little.g.springcloud.thirdpay.params;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * 退款参数
 *
 */
public class QueryRefundParams implements Serializable {

	// 退款id
	private String refundId;

	// 外部退款id
	private String outRefundId;

	@NotEmpty
	private String payOrderId;

	@NotEmpty
	private String outPayOrderId;

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

	@Override
	public String toString() {
		return "QueryRefundParams{" + "refundId='" + refundId + '\'' + ", outRefundId='"
				+ outRefundId + '\'' + ", payOrderId='" + payOrderId + '\''
				+ ", outPayOrderId='" + outPayOrderId + '\'' + '}';
	}

}
