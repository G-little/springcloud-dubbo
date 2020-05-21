package com.little.g.springcloud.user.oauth.api;

import com.github.scribejava.core.builder.api.DefaultApi20;
import com.github.scribejava.core.extractors.OAuth2AccessTokenJsonExtractor;
import com.github.scribejava.core.extractors.TokenExtractor;
import com.github.scribejava.core.httpclient.HttpClient;
import com.github.scribejava.core.httpclient.HttpClientConfig;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthConstants;
import com.github.scribejava.core.model.ParameterList;
import com.github.scribejava.core.oauth2.bearersignature.BearerSignature;
import com.github.scribejava.core.oauth2.bearersignature.BearerSignatureURIQueryParameter;
import com.github.scribejava.core.oauth2.clientauthentication.ClientAuthentication;
import com.github.scribejava.core.oauth2.clientauthentication.RequestBodyAuthenticationScheme;
import com.little.g.springcloud.user.oauth.service.WechatOAuthService;

import java.util.Map;

public class WechatApi20 extends DefaultApi20 {

	protected WechatApi20() {
	}

	public static WechatApi20 instance() {
		return WechatApi20.InstanceHolder.INSTANCE;
	}

	@Override
	public String getAccessTokenEndpoint() {
		return "https://api.weixin.qq.com/sns/oauth2/access_token";
	}

	@Override
	public String getAuthorizationUrl(String responseType, String apiKey, String callback,
			String scope, String state, Map<String, String> additionalParams) {
		final ParameterList parameters = new ParameterList(additionalParams);
		parameters.add(OAuthConstants.RESPONSE_TYPE, "code");
		parameters.add("appid", apiKey);

		if (callback != null) {
			parameters.add(OAuthConstants.REDIRECT_URI, callback);
		}

		if (scope != null) {
			parameters.add(OAuthConstants.SCOPE, scope);
		}

		if (state != null) {
			parameters.add(OAuthConstants.STATE, state);
		}
		parameters.add("connect_redirect", "1#wechat_redirect");

		return parameters.appendTo("https://open.weixin.qq.com/connect/oauth2/authorize");
	}

	@Override
	protected String getAuthorizationBaseUrl() {
		throw new UnsupportedOperationException("use getAuthorizationUrl instead");
	}

	@Override
	public TokenExtractor<OAuth2AccessToken> getAccessTokenExtractor() {
		return OAuth2AccessTokenJsonExtractor.instance();
	}

	/**
	 * 添加appId跟appKey采用在http的请求body中添加
	 * @return
	 */
	@Override
	public ClientAuthentication getClientAuthentication() {
		return RequestBodyAuthenticationScheme.instance();
	}

	/**
	 * 授权的token在http请求的body中传递
	 * @return
	 */
	@Override
	public BearerSignature getBearerSignature() {
		return BearerSignatureURIQueryParameter.instance();
	}

	@Override
	public WechatOAuthService createService(String apiKey, String apiSecret,
			String callback, String defaultScope, String responseType, String userAgent,
			HttpClientConfig httpClientConfig, HttpClient httpClient) {
		return new WechatOAuthService(this, apiKey, apiSecret, callback, defaultScope,
				responseType, userAgent, httpClientConfig, httpClient);
	}

	private static class InstanceHolder {

		private static final WechatApi20 INSTANCE = new WechatApi20();

		private InstanceHolder() {
		}

	}

}
