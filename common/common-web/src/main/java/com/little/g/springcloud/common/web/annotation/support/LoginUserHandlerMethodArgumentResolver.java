package com.little.g.springcloud.common.web.annotation.support;

import com.little.g.springcloud.common.web.annotation.LoginUser;
import com.little.g.springcloud.common.web.interceptor.HeaderParamsHolder;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class LoginUserHandlerMethodArgumentResolver
		implements HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return (parameter.getParameterType().isAssignableFrom(Long.class)
				|| parameter.getParameterType().isAssignableFrom(Integer.class))
				&& parameter.hasParameterAnnotation(LoginUser.class);
	}

	@Override
	public Object resolveArgument(MethodParameter parameter,
			ModelAndViewContainer container, NativeWebRequest request,
			WebDataBinderFactory factory) throws Exception {

		return HeaderParamsHolder.getHeader().getUid();
	}

}
