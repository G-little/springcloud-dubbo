package com.little.g.springcloud.mall.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * swagger在线文档配置<br>
 * 项目启动后可通过地址：http://host:ip/swagger-ui.html 查看在线文档
 *
 * @author enilu
 * @version 2018-07-24
 */

@Configuration
@EnableSwagger2
public class MallSwagger2Configuration {

	@Bean
	public Docket mallDocket() {

		return new Docket(DocumentationType.SWAGGER_2).groupName("mall")
				.useDefaultResponseMessages(false).apiInfo(adminApiInfo()).select()
				.apis(RequestHandlerSelectors
						.basePackage("com.little.g.springcloud.mall.web"))
				.paths(PathSelectors.regex("^(?!auth).*$")).build()
				.securitySchemes(securitySchemes()).securityContexts(securityContexts());
	}

	private List<ApiKey> securitySchemes() {
		return newArrayList(new ApiKey("Authorization", "Authorization", "header"));
	}

	private List<SecurityContext> securityContexts() {
		return newArrayList(SecurityContext.builder().securityReferences(defaultAuth())
				.forPaths(PathSelectors.regex("^(?!auth).*$")).build());
	}

	List<SecurityReference> defaultAuth() {
		AuthorizationScope authorizationScope = new AuthorizationScope("global",
				"accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		return newArrayList(new SecurityReference("Authorization", authorizationScopes));
	}

	private ApiInfo adminApiInfo() {
		return new ApiInfoBuilder().title("mall API").description("商城Api").version("1.0")
				.build();
	}

}
