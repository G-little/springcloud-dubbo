package com.little.g.springcloud.admin.web.config;

import com.little.g.springcloud.admin.web.interceptor.LoginInterceptor;
import com.little.g.springcloud.common.web.interceptor.HeaderInterceptor;
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
		registry.addInterceptor(loginInterceptor()).addPathPatterns("/**")
				.excludePathPatterns("/h_layout/**").excludePathPatterns("/jsp/**")
				.excludePathPatterns("/error").excludePathPatterns("/404")
				.excludePathPatterns("/images/**").excludePathPatterns("/js/**")
				.excludePathPatterns("/css/**").excludePathPatterns("/layui/**")
				.excludePathPatterns("/layer/**")
				.excludePathPatterns("/startbootstrap/**")
				.excludePathPatterns("/admin/login").excludePathPatterns("/favicon.ico")
				.excludePathPatterns("/login").excludePathPatterns("/login_code");
		registry.addInterceptor(headerInterceptor()).addPathPatterns("/**");
	}

	@Bean
	public LoginInterceptor loginInterceptor() {
		return new LoginInterceptor();
	}

	@Bean
	public HeaderInterceptor headerInterceptor() {
		return new HeaderInterceptor();
	}

}
