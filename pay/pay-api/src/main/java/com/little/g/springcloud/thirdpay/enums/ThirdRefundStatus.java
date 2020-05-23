package com.little.g.springcloud.thirdpay.enums;

/**
 * 第三方退款状态
 *
 * @author zouxuefeng at 2015-11-07 15:02
 */
public enum ThirdRefundStatus {

	/**
	 * 退款中
	 */
	REFUNDING((byte) 0),
	/**
	 * 成功
	 */
	SUCCESS((byte) 1),
	/**
	 * 失败
	 */
	FAIL((byte) 2),

	/**
	 * 需转入人工退款
	 */
	TURN_TO_USER((byte) 5);
	byte status;

	ThirdRefundStatus(byte status) {
		this.status = status;
	}

	public byte getStatus() {
		return this.status;
	}

	public static ThirdRefundStatus statusOf(byte status) {
		for (ThirdRefundStatus refundStatus : ThirdRefundStatus.values()) {
			if (refundStatus.status == status) {
				return refundStatus;
			}
		}

		throw new RuntimeException("no RefundStatus from status:" + status);
	}

}
