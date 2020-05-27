package com.little.g.springcloud.common.web.config;

import com.little.g.springcloud.common.web.exception.GlobalExceptionHandler;
import com.little.g.springcloud.common.web.exception.GlobalExceptionResolver;
import com.little.g.springcloud.common.web.utils.ReloadableResourceBundleMessageSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Locale;

/**
 * Created by lengligang on 2019/3/12.
 */
@Configuration
public class AppConfig {

	@Value("${spring.messages.basename}")
	private String baseName;

	@Resource
	private ResourceLoader resourceLoader;

	@Bean
	GlobalExceptionHandler exceptionHandler() {
		return new GlobalExceptionHandler();
	}

	@Bean
	GlobalExceptionResolver globalExceptionResolver() {
		return new GlobalExceptionResolver();
	}

	@Bean
	public ErrorAttributes errorAttributes() {
		return new PriestErrorAttributes();
	}

	/**
	 * 默认解析器 其中locale表示默认语言
	 */
	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		localeResolver.setDefaultLocale(Locale.CHINA);
		return localeResolver;
	}

	/**
	 * 默认拦截器 其中lang表示切换语言的参数名
	 */
	@Bean
	public WebMvcConfigurer localeInterceptor() {
		return new WebMvcConfigurer() {
			@Override
			public void addInterceptors(InterceptorRegistry registry) {
				LocaleChangeInterceptor localeInterceptor = new LocaleChangeInterceptor();
				localeInterceptor.setParamName("lang");
				registry.addInterceptor(localeInterceptor);
			}
		};
	}

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename(baseName);
		messageSource.setCacheSeconds(3600);
		return messageSource;
	}

	@Bean
	public LocalValidatorFactoryBean getValidator() {
		LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
		bean.setValidationMessageSource(messageSource());
		return bean;
	}

	@Bean
	public MessageSourceUtil messageSourceUtil() {
		return new MessageSourceUtil();
	}

	@PostConstruct
	public void init() {
		ResourcePatternResolver resolver = ResourcePatternUtils
				.getResourcePatternResolver(resourceLoader);
		MetadataReaderFactory metaReader = new CachingMetadataReaderFactory(
				resourceLoader);
		org.springframework.core.io.Resource[] resources = new org.springframework.core.io.Resource[0];
		try {
			resources = resolver
					.getResources("classpath*:com/little/g/**/*ErrorCodes.class");

			for (org.springframework.core.io.Resource r : resources) {
				MetadataReader reader = metaReader.getMetadataReader(r);

				if ("com.little.g.springcloud.common.error.ErrorCodes"
						.equals(reader.getClassMetadata().getSuperClassName())) {
					// 说明是子类
					try {
						Class c = Class.forName(reader.getClassMetadata().getClassName());
						Object o = Class.forName(reader.getClassMetadata().getClassName())
								.newInstance();
						Method m = c.getDeclaredMethod("addCodes");
						m.setAccessible(true);
						m.invoke(o);

					} catch (Exception e) {
						throw new RuntimeException(e);
					}

				}
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

}
