package com.little.g.springcloud.admin.web.annotation;

import com.little.g.springcloud.admin.enums.LogicalEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface RequiresPermissions {

	String[] value();

	/**
	 * The logical operation for the permission checks in case multiple roles are
	 * specified. AND is the default
	 *
	 * @since 1.1.0
	 */
	LogicalEnum logical() default LogicalEnum.AND;

}
