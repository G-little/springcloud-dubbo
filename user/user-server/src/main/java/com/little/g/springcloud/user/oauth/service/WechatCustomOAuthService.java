package com.little.g.springcloud.user.oauth.service;

import com.alibaba.fastjson.JSONObject;
import com.github.scribejava.core.exceptions.OAuthException;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;
import com.little.g.springcloud.common.enums.GenderEnum;
import com.little.g.springcloud.common.exception.ServiceDataException;
import com.little.g.springcloud.user.model.OAuthUser;
import com.little.g.springcloud.user.oauth.model.WechatOAuthUser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WechatCustomOAuthService implements CustomOAuthService {

	private final OAuth20Service oAuth20Service;

	private final String oAuthType;

	private static final String queryUserInfoURL = "https://api.weixin.qq.com/sns/userinfo";

	private static final Pattern OPENID_REGEX_PATTERN = Pattern
			.compile("\"openid\"\\s*:\\s*\"(\\S*?)\"");

	public WechatCustomOAuthService(OAuth20Service oAuth20Service, String oAuthType) {
		this.oAuth20Service = oAuth20Service;
		this.oAuthType = oAuthType;
	}

	@Override
	public String getoAuthType() {
		return this.oAuthType;
	}

	@Override
	public String getAuthorizationUrl() {
		return oAuth20Service.createAuthorizationUrlBuilder().scope("snsapi_userinfo")
				.build();
	}

	@Override
	public OAuthUser getOAuthUser(OAuth2AccessToken accessToken) {
		OAuthRequest request = new OAuthRequest(Verb.GET, queryUserInfoURL);
		// 提取openId
		String tokenResponse = accessToken.getRawResponse();
		String openId = extractParameter(tokenResponse, OPENID_REGEX_PATTERN, true);
		request.addParameter("openid", openId);
		oAuth20Service.signRequest(accessToken, request);
		try {
			Response response = oAuth20Service.execute(request);
			WechatOAuthUser wechatUser = JSONObject.parseObject(response.getBody(),
					WechatOAuthUser.class);
			OAuthUser oAuthUser = new OAuthUser();
			oAuthUser.setAccessToken(accessToken.getAccessToken());
			oAuthUser.setAvatar(wechatUser.getHeadimgurl());
			oAuthUser.setExpiresIn(accessToken.getExpiresIn());
			if (wechatUser.getSex() != null) {
				oAuthUser.setGender(wechatUser.getSex() - 1 == 0
						? GenderEnum.MAN.getValue() : GenderEnum.WOMEN.getValue());
			}
			oAuthUser.setName(wechatUser.getNickname());
			oAuthUser.setOauthType(this.oAuthType);
			oAuthUser.setOpenid(wechatUser.getOpenid());
			oAuthUser.setRefreshToken(accessToken.getRefreshToken());
			oAuthUser.setUnionid(wechatUser.getUnionid());
			oAuthUser.setUpdateTime(System.currentTimeMillis());
			return oAuthUser;

		}
		catch (Exception e) {
			throw new ServiceDataException(e);
		}
	}

	@Override
	public OAuth2AccessToken getToken(String code) {
		try {
			return oAuth20Service.getAccessToken(code);
		}
		catch (Exception e) {
			throw new ServiceDataException(e);
		}
	}

	protected static String extractParameter(String response, Pattern regexPattern,
			boolean required) throws OAuthException {
		final Matcher matcher = regexPattern.matcher(response);
		if (matcher.find()) {
			return matcher.group(1);
		}

		if (required) {
			throw new OAuthException(
					"Response body is incorrect. Can't extract a '"
							+ regexPattern.pattern() + "' from this: '" + response + "'",
					null);
		}

		return null;
	}

}
