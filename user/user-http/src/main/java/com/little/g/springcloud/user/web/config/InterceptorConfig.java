package com.little.g.springcloud.user.web.config;

import com.little.g.springcloud.common.web.aspects.ResultCodeI18NAspect;
import com.little.g.springcloud.common.web.interceptor.HeaderInterceptor;
import com.little.g.springcloud.common.web.interceptor.TokenVerifyInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by lengligang on 2019/3/25.
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(tokenVerifyInterceptor()).addPathPatterns("/**")
				.excludePathPatterns("/user/sendsms").excludePathPatterns("/user/joinin");
		registry.addInterceptor(headerInterceptor()).addPathPatterns("/**");
	}

	@Bean
	public HeaderInterceptor headerInterceptor() {
		return new HeaderInterceptor();
	}

	@Bean
	public TokenVerifyInterceptor tokenVerifyInterceptor() {
		return new TokenVerifyInterceptor();
	}

	@Bean
	ResultCodeI18NAspect i18NAspect() {
		return new ResultCodeI18NAspect();
	}

}
