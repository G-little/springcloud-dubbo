package com.little.g.springcloud.common.validate.validators;

import com.little.g.springcloud.common.validate.annatations.PayType;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PayTypeValidator implements ConstraintValidator<PayType, String> {

	@Override
	public void initialize(PayType constraint) {
	}

	@Override
	public boolean isValid(String obj, ConstraintValidatorContext context) {
		return com.little.g.springcloud.common.enums.PayType.ALIPAY.equals(obj)
				|| com.little.g.springcloud.common.enums.PayType.WEXINPAY.equals(obj);
	}

}
