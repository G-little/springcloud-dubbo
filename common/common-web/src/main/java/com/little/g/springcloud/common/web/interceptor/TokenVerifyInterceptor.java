package com.little.g.springcloud.common.web.interceptor;

import com.auth0.jwt.JWT;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.little.g.springcloud.common.error.CommonErrorCodes;
import com.little.g.springcloud.common.exception.ServiceDataException;
import com.little.g.springcloud.user.api.TokenService;
import com.little.g.springcloud.user.dto.TokenCache;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * Created by lengligang on 2019/3/30.
 */
public class TokenVerifyInterceptor extends HandlerInterceptorAdapter {

	@Reference
	private TokenService tokenService;

	private static final Logger log = LoggerFactory
			.getLogger(TokenVerifyInterceptor.class);

	private LoadingCache<String, TokenCache> cache = CacheBuilder.newBuilder()
			.maximumSize(100000).expireAfterWrite(10, TimeUnit.MINUTES)
			.build(new CacheLoader<String, TokenCache>() {
				@Override
				public TokenCache load(String s) throws Exception {
					if (StringUtils.isEmpty(s)) {
						return null;
					}
					String[] credentials = s.split("@_");
					String token = credentials[0];
					String deviceId = credentials[1];

					return tokenService.verify(deviceId, token);
				}
			});

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
							 Object handler) throws Exception {

		/* 用户Token */
		String token = request.getHeader("Authorization");

		if (StringUtils.isEmpty(token)) {
			throw new ServiceDataException(CommonErrorCodes.NOT_LOGIN,
					"msg.user.not.login");
		}
		/* 设备ID */
		String deviceId = JWT.decode(token).getAudience().get(1);

		if (StringUtils.isEmpty(deviceId)) {
			throw new ServiceDataException(CommonErrorCodes.NOT_LOGIN,
					"msg.user.not.login");
		}

		String localKey = String.format("%s@_%s", token, deviceId);

		TokenCache tokenCache = cache.get(localKey);
		if (tokenCache != null && tokenCache.isLogin()) {
			HeaderParamsHolder.getHeader().setUid(tokenCache.getUid());
			return true;
		}

		throw new ServiceDataException(CommonErrorCodes.NOT_LOGIN, "msg.user.not.login");
	}

}
