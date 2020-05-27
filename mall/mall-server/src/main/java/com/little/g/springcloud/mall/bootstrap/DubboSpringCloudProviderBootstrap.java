/*
 * Copyright 2013-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.little.g.springcloud.mall.bootstrap;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * Dubbo Spring Cloud Provider Bootstrap.
 */

@EnableApolloConfig("application.yaml")
@EnableDiscoveryClient
@EnableAutoConfiguration
@ComponentScan({ "com.little.g.springcloud.pay.service.impl",
		"com.little.g.springcloud.thirdpay.service.impl",
		"com.little.g.springcloud.common.cache" })
@MapperScan({ "com.little.g.**.mapper" })
public class DubboSpringCloudProviderBootstrap {

	@Bean
	public ReloadableResourceBundleMessageSource messageSource() {
		ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
		source.setBasename("i18n/messages");
		source.setCacheSeconds(3600);
		return source;
	}

	public static void main(String[] args) {
		new SpringApplicationBuilder(DubboSpringCloudProviderBootstrap.class)
				.properties("spring.profiles.active=nacos").web(WebApplicationType.NONE)
				.run(args);
	}

}
