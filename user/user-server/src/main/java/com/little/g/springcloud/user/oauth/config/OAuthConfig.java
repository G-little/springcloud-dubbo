package com.little.g.springcloud.user.oauth.config;

import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.oauth.OAuth20Service;
import com.little.g.springcloud.user.oauth.api.WechatApi20;
import com.little.g.springcloud.user.oauth.service.CustomOAuthService;
import com.little.g.springcloud.user.oauth.service.WechatCustomOAuthService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lengligang on 2019/4/16.
 */
@Configuration
public class OAuthConfig {

	private static final String CALLBACK_URL = "http://192.168.9.114:8080/oauth/%s/callback";

	@Value("${oAuth.weixin.appId}")
	private String weixinAppId;

	@Value("${oAuth.weixin.appSecret}")
	String weixinAppSecret;

	@Bean
	public List<CustomOAuthService> getCustomOAuthServiceList() {
		List<CustomOAuthService> list = new ArrayList<>();
		CustomOAuthService wechat = new WechatCustomOAuthService(
				new ServiceBuilder(weixinAppId).apiSecret(weixinAppSecret)
						.callback(String.format(CALLBACK_URL, OAuthTypes.WEIXIN))
						.defaultScope("snsapi_userinfo").build(WechatApi20.instance()),
				OAuthTypes.WEIXIN);
		list.add(wechat);
		return list;
	}

	@Bean
	public OAuth20Service getWeixinService() {
		return new ServiceBuilder(weixinAppId).apiSecret(weixinAppSecret)
				.defaultScope("snsapi_login")
				.callback(String.format(CALLBACK_URL, OAuthTypes.WEIXIN))
				.build(WechatApi20.instance());
	}

}
