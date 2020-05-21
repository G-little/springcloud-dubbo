package com.little.g.springcloud.common.validate.annatations;

import com.little.g.springcloud.common.validate.validators.PayTypeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * User: erin Date: 15/5/29 Time: 下午8:09
 */
@Target({ METHOD, FIELD, ANNOTATION_TYPE, PARAMETER })
@Retention(RUNTIME)
@Constraint(validatedBy = PayTypeValidator.class)
@Documented
public @interface PayType {

	String message() default "支付类型不正确";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
