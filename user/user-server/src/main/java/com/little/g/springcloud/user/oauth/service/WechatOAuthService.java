package com.little.g.springcloud.user.oauth.service;

import com.github.scribejava.core.builder.api.DefaultApi20;
import com.github.scribejava.core.httpclient.HttpClientConfig;
import com.github.scribejava.core.model.OAuthConstants;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.oauth.AccessTokenRequestParams;
import com.github.scribejava.core.oauth.OAuth20Service;

public class WechatOAuthService extends OAuth20Service {

	public WechatOAuthService(DefaultApi20 api, String apiKey, String apiSecret,
			String callback, String defaultScope, String responseType, String userAgent,
			HttpClientConfig httpClientConfig,
			com.github.scribejava.core.httpclient.HttpClient httpClient) {
		super(api, apiKey, apiSecret, callback, defaultScope, responseType, userAgent,
				httpClientConfig, httpClient);
	}

	@Override
	protected OAuthRequest createAccessTokenRequest(AccessTokenRequestParams params) {
		final DefaultApi20 api = getApi();
		final OAuthRequest request = new OAuthRequest(api.getAccessTokenVerb(),
				api.getAccessTokenEndpoint());
		request.addBodyParameter("appid", getApiKey());
		request.addBodyParameter("secret", getApiSecret());
		request.addBodyParameter(OAuthConstants.GRANT_TYPE,
				OAuthConstants.AUTHORIZATION_CODE);
		request.addBodyParameter(OAuthConstants.CODE, params.getCode());
		return request;
	}

}
