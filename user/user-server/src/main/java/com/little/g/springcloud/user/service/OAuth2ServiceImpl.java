package com.little.g.springcloud.user.service;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.little.g.springcloud.common.ResultJson;
import com.little.g.springcloud.common.enums.DeviceTypeEnum;
import com.little.g.springcloud.common.exception.ServiceDataException;
import com.little.g.springcloud.user.UserErrorCodes;
import com.little.g.springcloud.user.api.OAuth2Service;
import com.little.g.springcloud.user.api.UserService;
import com.little.g.springcloud.user.dto.UserDTO;
import com.little.g.springcloud.user.mapper.OAuthUserMapper;
import com.little.g.springcloud.user.model.OAuthUser;
import com.little.g.springcloud.user.model.OAuthUserKey;
import com.little.g.springcloud.user.oauth.service.CustomOAuthService;
import com.little.g.springcloud.user.oauth.service.OAuthServices;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * Created by lengligang on 2019/4/17.
 */
@Service("oauth2Service")
public class OAuth2ServiceImpl implements OAuth2Service {

	@Resource
	private OAuthServices oAuthServices;

	@Resource
	private OAuthUserMapper oAuthUserMapper;

	@Resource
	private UserService userService;

	@Override
	@Transactional
	public ResultJson login(@NotEmpty @Size(max = 10) String type,
			@NotEmpty @Size(max = 100) String code, @Size(max = 100) String deviceId,
			Byte deviceType) {
		ResultJson r = new ResultJson();

		CustomOAuthService customOAuthService = oAuthServices.getOAuthService(type);
		if (customOAuthService == null) {
			r.setC(UserErrorCodes.USER_ERROR);
			r.setM("msg.oauth2.unsupport.type");
			return r;
		}
		OAuth2AccessToken token = customOAuthService.getToken(code);
		if (token == null) {
			r.setC(UserErrorCodes.USER_ERROR);
			r.setM("msg.oauth2.code.invalid");
		}
		OAuthUser oAuthUser = customOAuthService.getOAuthUser(token);
		if (oAuthUser == null) {
			r.setC(UserErrorCodes.USER_ERROR);
			r.setM("msg.oauth2.userinfo.failed");
		}
		// 查询用户信息是否已存在
		OAuthUserKey key = new OAuthUserKey();
		key.setOauthType(type);
		key.setOpenid(oAuthUser.getOpenid());

		if (StringUtils.isEmpty(deviceId)) {
			deviceId = oAuthUser.getOpenid();
		}

		if (deviceType == null || deviceType <= 0) {
			deviceType = DeviceTypeEnum.MOBILE.getValue();
		}

		OAuthUser oAuthUserRepo = oAuthUserMapper.selectByPrimaryKey(key);

		OAuthUser mixOAuthUser;
		if (oAuthUserRepo == null) {
			// 创建用户
			mixOAuthUser = oAuthUser;
			UserDTO user = new UserDTO();
			BeanUtils.copyProperties(mixOAuthUser, user);
			Long uid = userService.addUser(user);

			mixOAuthUser.setUid(uid);
			int row = oAuthUserMapper.insertSelective(mixOAuthUser);
			if (row <= 0) {
				throw new ServiceDataException(ResultJson.SYSTEM_UNKNOWN_EXCEPTION);
			}

			r.setData(userService.createLoginReturn(deviceId, deviceType, null, user));

		}
		else {
			// 更新三方用户信息
			mixOAuthUser = oAuthUserRepo;
			BeanUtils.copyProperties(oAuthUser, mixOAuthUser, null, "uid", "name",
					"avatar", "gender", "unionid");
			int row = oAuthUserMapper.updateByPrimaryKey(mixOAuthUser);
			if (row <= 0) {
				throw new ServiceDataException(ResultJson.SYSTEM_UNKNOWN_EXCEPTION);
			}
			UserDTO user = userService.getUserById(mixOAuthUser.getUid());
			r.setData(userService.createLoginReturn(deviceId, deviceType, null, user));
		}

		return r;
	}

}
