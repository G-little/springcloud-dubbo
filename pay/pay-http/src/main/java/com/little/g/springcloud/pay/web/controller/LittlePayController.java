package com.little.g.springcloud.pay.web.controller;

import com.little.g.springcloud.common.ResultJson;
import com.little.g.springcloud.common.enums.PayType;
import com.little.g.springcloud.common.utils.MoneyUtil;
import com.little.g.springcloud.common.web.interceptor.HeaderParamsHolder;
import com.little.g.springcloud.pay.api.ChargeService;
import com.little.g.springcloud.pay.api.LittlePayService;
import com.little.g.springcloud.pay.dto.OrderResult;
import com.little.g.springcloud.pay.dto.PayTypeDTO;
import com.little.g.springcloud.thirdpay.api.ThirdpayApi;
import com.little.g.springcloud.thirdpay.dto.PayCallbackInfo;
import com.little.g.springcloud.thirdpay.dto.PayResponseInfo;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@RequestMapping("/pay")
@RestController
public class LittlePayController {

	@Reference
	private LittlePayService littlePayService;

	@Reference
	private ChargeService chargeService;

	@Reference
	private ThirdpayApi thirdpayApi;

	@RequestMapping("/list")
	public ResultJson list() {
		List<PayTypeDTO> typeList = littlePayService.typeList();
		ResultJson r = new ResultJson();
		r.setData(typeList);
		return r;
	}

	@RequestMapping
	public ResultJson pay(@RequestParam String preorderNo) {
		Long uid = HeaderParamsHolder.getHeader().getUid();
		return littlePayService.pay(uid, preorderNo);
	}

	@RequestMapping("/charge")
	public ResultJson charge(@RequestParam Double money) {
		Long uid = HeaderParamsHolder.getHeader().getUid();
		Long moneyFen = MoneyUtil.double2Long(money);
		OrderResult r = chargeService.createChargeOrder(uid, moneyFen);

		ResultJson result = new ResultJson();
		result.setData(r);
		return result;

	}

	@RequestMapping("/{payType}/thirdpay")
	public ResultJson thirdpay(@PathVariable("payType") String payType,
			@RequestParam String preorderNo) {
		Long uid = HeaderParamsHolder.getHeader().getUid();
		return littlePayService.thirdpay(uid, payType, preorderNo);
	}

	/**
	 * 获取支付参数
	 * @param payType
	 * @param preorderNo
	 * @return
	 */
	@RequestMapping("/{payType}/params")
	public ResultJson params(@PathVariable("payType") String payType,
			@RequestParam String preorderNo) {
		Long uid = HeaderParamsHolder.getHeader().getUid();
		return littlePayService.prePay(uid, payType, preorderNo);
	}

	@RequestMapping(value = "/{payType}/callback")
	@ResponseBody
	public String callback(@PathVariable("payType") String payType,
			@RequestBody String body, HttpServletRequest request) {
		PayCallbackInfo callbackInfo;
		if (PayType.WEXINPAY.equals(payType)) {
			callbackInfo = thirdpayApi.verifyBodyResponse(payType, body);
		}
		else {
			Map<String, String> params = new HashMap<String, String>();
			Map requestParams = request.getParameterMap();
			for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
				String name = (String) iter.next();
				String[] values = (String[]) requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i]
							: valueStr + values[i] + ",";
				}
				// 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
				// valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
				params.put(name, valueStr);
			}
			callbackInfo = thirdpayApi.verifyResponse(payType, params);
		}
		littlePayService.thirdpayCallback(payType, callbackInfo);

		PayResponseInfo resp = thirdpayApi.successPayResponse(payType);

		return resp.getResponse();
	}

}
