package com.little.g.springcloud.thirdpay.params;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * 第三方查询信息
 *
 */
public class QueryPayParams implements Serializable {

	// 系统支付订单id
	@NotEmpty
	private String payOrderId;

	// 第三方支付订单id
	private String outPayOrderId;

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
		return "QueryPayParams{" + "payOrderId='" + payOrderId + '\''
				+ ", outPayOrderId='" + outPayOrderId + '\'' + '}';
	}

}
