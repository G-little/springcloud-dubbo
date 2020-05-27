package com.little.g.springcloud.mall.web.config;

import com.little.g.springcloud.common.web.aspects.ResultCodeI18NAspect;
import com.little.g.springcloud.common.web.config.WebMvcConfiguration;
import com.little.g.springcloud.common.web.interceptor.HeaderInterceptor;
import com.little.g.springcloud.common.web.interceptor.TokenVerifyInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by lengligang on 2019/3/25.
 */
@Configuration
@Import(WebMvcConfiguration.class)
public class InterceptorConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(tokenVerifyInterceptor()).addPathPatterns("/**")
				.excludePathPatterns("/favicon.ico", "/static/**")
				.excludePathPatterns("/user/sendsms").excludePathPatterns("/user/joinin");
		registry.addInterceptor(headerInterceptor()).addPathPatterns("/**");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/favicon.ico", "/static/**")
				.addResourceLocations("/static/**");
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
