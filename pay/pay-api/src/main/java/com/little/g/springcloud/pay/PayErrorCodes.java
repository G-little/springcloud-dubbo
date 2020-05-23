
package com.little.g.springcloud.pay;

import com.little.g.springcloud.common.error.ErrorCodeDiv;
import com.little.g.springcloud.common.error.ErrorCodes;

/**
 * Created by lengligang on 2019/3/12.
 */
public class PayErrorCodes extends ErrorCodes {

	public static final Integer PAY_ERROR = 40000;

	public static final Integer THIRDPAY_ERROR = 40100;

	public PayErrorCodes() {
		super(ErrorCodeDiv.PAY);
	}

	@Override
	protected void addCodes() {
		addCode2Map(PAY_ERROR, "mag.pay.error");
		addCode2Map(THIRDPAY_ERROR, "msg.thirdpay.error");
	}

}
