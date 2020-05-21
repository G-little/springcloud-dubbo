package com.little.g.springcloud.user.oauth.api;

import com.little.g.springcloud.user.oauth.service.CustomOAuthService;

public interface OAuthServicesApi {

	CustomOAuthService getOAuthService(String type);

}
