package com.little.g.springcloud.common.validate.validators;

import com.little.g.springcloud.common.validate.annatations.SmsInterType;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * User: erin Date: 15/5/29 Time: 下午8:16
 */
public class SmsInterTypeValidator implements ConstraintValidator<SmsInterType, Byte> {

	@Override
	public void initialize(SmsInterType smsInterType) {

	}

	@Override
	public boolean isValid(Byte smsInterType,
			ConstraintValidatorContext constraintValidatorContext) {
		if (smsInterType == null) {
			return true;
		}
		if (smsInterType == 1 || smsInterType == 2 || smsInterType == 3
				|| smsInterType == 4 || smsInterType == 5) {
			return true;
		}
		return false;
	}

}
