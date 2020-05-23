package com.little.g.springcloud.thirdpay.enums;

/**
 *
 */
public enum ThirdPayStatus {

	/**
	 * 等待支付
	 */
	WAIT_PAY,
	/**
	 * 支付中
	 */
	PAYING,
	/**
	 * 支付成功
	 */
	SUCCESS,
	/**
	 * 支付失败
	 */
	FAIL,
	/**
	 * 取消支付
	 */
	CANCEL,
	/**
	 * 支付超时
	 */
	TIMEOUT,
	/**
	 * 无相关订单
	 */
	NO_SUCH_ORDER,
	/**
	 * 已退款
	 */
	REFUND,
	/**
	 * 未知
	 */
	UNKNOWN;

}
