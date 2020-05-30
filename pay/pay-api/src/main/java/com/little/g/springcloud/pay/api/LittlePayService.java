package com.little.g.springcloud.pay.api;

import com.little.g.springcloud.common.ResultJson;
import com.little.g.springcloud.common.validate.annatations.PayType;
import com.little.g.springcloud.pay.dto.PayTypeDTO;
import com.little.g.springcloud.thirdpay.dto.PayCallbackInfo;
import com.little.g.springcloud.thirdpay.dto.PreRefundResult;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface LittlePayService {

	/**
	 * 支付方式列表
	 * @return
	 */
	List<PayTypeDTO> typeList();

	/**
	 * 支付参数生成
	 * @param payType
	 * @param preorderNo
	 * @return
	 */
	ResultJson prePay(@NotBlank Long uid, @NotEmpty String payType,
			@NotEmpty String preorderNo);

	/**
	 * 使用三方支付订单
	 * @param uid
	 * @param payType
	 * @param preorderNo
	 * @return
	 */
	ResultJson thirdpay(@NotBlank Long uid, @NotEmpty String payType,
			@NotEmpty String preorderNo);

	/**
	 * 余额支付
	 * @param uid
	 * @param preorderNo
	 * @return
	 */
	ResultJson pay(@NotBlank Long uid, @NotEmpty String preorderNo);

	/**
	 * 三方支付结果回调
	 * @param payType
	 * @param callbackInfo
	 */
	void thirdpayCallback(@PayType String payType, @NotNull PayCallbackInfo callbackInfo);

	/**
	 * 退款
	 * @param uid 用户
	 * @param payType 支付类型
	 * @param orderNo 订单号
	 * @param money 金额
	 * @return 退款结果
	 */
	PreRefundResult refund(Long uid, @NotEmpty String payType, @NotEmpty String orderNo,
			Long total, Long money);

}
