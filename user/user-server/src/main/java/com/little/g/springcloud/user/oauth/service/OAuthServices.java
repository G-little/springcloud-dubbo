package com.little.g.springcloud.user.oauth.service;

import com.little.g.springcloud.user.oauth.api.OAuthServicesApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OAuthServices implements OAuthServicesApi {

	@Autowired
	List<CustomOAuthService> customOAuthServiceList;

	@Override
	public CustomOAuthService getOAuthService(String type) {
		Optional<CustomOAuthService> oAuthService = customOAuthServiceList.stream()
				.filter(o -> o.getoAuthType().equals(type)).findFirst();
		if (oAuthService.isPresent()) {
			return oAuthService.get();
		}
		return null;
	}

}
