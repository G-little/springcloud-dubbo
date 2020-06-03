package com.little.g.springcloud.user.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.little.g.springcloud.common.ResultJson;
import com.little.g.springcloud.common.enums.DeviceTypeEnum;
import com.little.g.springcloud.common.enums.TokenVersion;
import com.little.g.springcloud.common.exception.ServiceDataException;
import com.little.g.springcloud.user.api.TokenService;
import com.little.g.springcloud.user.common.RedisConstants;
import com.little.g.springcloud.user.dto.TokenCache;
import com.little.g.springcloud.user.dto.UserDeviceTokenDTO;
import com.little.g.springcloud.user.mapper.UserDeviceTokenMapper;
import com.little.g.springcloud.user.model.UserDeviceToken;
import com.little.g.springcloud.user.model.UserDeviceTokenExample;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Created by lengligang on 2019/3/29.
 */
@Slf4j
@Service(protocol = "dubbo")
public class TokenServiceImpl implements TokenService {

    int version = TokenVersion.VERSION_2.getValue();

    @Resource
    private UserDeviceTokenMapper userDeviceTokenMapper;

    @Resource
    private RedisTemplate<String, UserDeviceTokenDTO> redisTemplate;

    @Override
    public UserDeviceTokenDTO createToken(@NotBlank @Min(1) Integer uid,
                                          @NotBlank @Size(min = 3, max = 50) String deviceId, @NotBlank Byte deviceType,
                                          @Size(min = 1, max = 100) String os) {
        return createToken(uid, deviceId, deviceType, os, false);
    }

    private UserDeviceTokenDTO createToken(@NotBlank @Min(1) Integer uid,
                                           @NotBlank @Size(min = 3, max = 50) String deviceId, @NotBlank Byte deviceType,
                                           @Size(min = 1, max = 100) String os, boolean refresh) {

        String pass = RandomStringUtils.randomAlphabetic(10, 20);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 2);
        String accessToken = JWT.create().withAudience(String.valueOf(uid), deviceId)
                .withExpiresAt(calendar.getTime()).sign(Algorithm.HMAC256(pass));

        Long accessExpire = calendar.getTimeInMillis();

        calendar.add(Calendar.MONTH, 2);

        Long refreshExpire = calendar.getTimeInMillis();

        String refreshToken = RandomStringUtils.randomAlphabetic(15, 20);

        UserDeviceTokenDTO userDeviceTokenDTO = new UserDeviceTokenDTO(uid, deviceId,
                accessToken);
        userDeviceTokenDTO.setPass(pass);
        if (deviceType != null && DeviceTypeEnum.checkKeyIsExist(deviceType)) {
            userDeviceTokenDTO.setDeviceType(deviceType);
        } else {
            // 默认为手机端
            userDeviceTokenDTO.setDeviceType(DeviceTypeEnum.MOBILE.getValue());
        }
        userDeviceTokenDTO.setRefreshToken(refreshToken);
        userDeviceTokenDTO.setOs(os);

        userDeviceTokenDTO.setAccessExpiresIn(accessExpire);
        userDeviceTokenDTO.setRefreshExpiresIn(refreshExpire);

        UserDeviceTokenExample example = new UserDeviceTokenExample();
        // 同一个设备只能有一台登录
        example.or().andDeviceIdEqualTo(deviceId);
        // 同类型设备只能有一台
        example.or().andUidEqualTo(uid).andDeviceTypeEqualTo(deviceType);
        example.setOrderByClause("id desc limit 20");

        List<UserDeviceToken> tokenList = userDeviceTokenMapper.selectByExample(example);

        if (!CollectionUtils.isEmpty(tokenList)) {
            UserDeviceTokenExample updateExample = new UserDeviceTokenExample();
            updateExample.or().andIdIn(tokenList.stream().map(token -> token.getId())
                    .collect(Collectors.toList()));
            userDeviceTokenMapper.deleteByExample(updateExample);
            // 清理redis缓存
            for (UserDeviceToken token : tokenList) {
                if (token.getDeviceId().equals(deviceId)) {
                    // 刷新token 使久token 30s 内有效
                    token.setAccessExpiresIn(
                            System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(30));
                    token.setRefreshExpiresIn(0L);
                    UserDeviceTokenDTO dto = new UserDeviceTokenDTO();
                    BeanUtils.copyProperties(token, dto);
                    redisTemplate.opsForValue().set(getTokenKey(token.getAccessToken()),
                            dto, 30, TimeUnit.SECONDS);
                } else {
                    redisTemplate.delete(getTokenKey(token.getAccessToken()));
                }
            }

        }

        UserDeviceToken token = new UserDeviceToken();
        BeanUtils.copyProperties(userDeviceTokenDTO, token);

        if (userDeviceTokenMapper.insert(token) > 0) {
            //
            userDeviceTokenDTO.setId(token.getId());
            redisTemplate.opsForValue().set(getTokenKey(token.getAccessToken()),
                    userDeviceTokenDTO, 30, TimeUnit.DAYS);
        }

        return userDeviceTokenDTO;
    }

    @Override
    public TokenCache verify(@NotBlank @Size(min = 3, max = 50) String deviceId,
                             @NotBlank @Size(min = 3, max = 50) String token) {

        TokenCache tokenCache = new TokenCache();
        tokenCache.setDeviceId(deviceId);
        UserDeviceTokenDTO userDeviceTokenDTO = redisTemplate.opsForValue()
                .get(getTokenKey(token));
        if (userDeviceTokenDTO == null) {

            // 数据库查询
            userDeviceTokenDTO = getDeviceToken(deviceId, token);

            if (userDeviceTokenDTO == null) {
                return tokenCache;
            }

            JWTVerifier jwtVerifier = JWT
                    .require(Algorithm.HMAC256(userDeviceTokenDTO.getPass())).build();
            try {
                jwtVerifier.verify(token);
            } catch (JWTVerificationException e) {
                log.error("jwt token verify exception token:{},pass:{},e:{}", token,
                        userDeviceTokenDTO.getPass(), e);
                tokenCache.setLogin(false);
                return tokenCache;
            }

            redisTemplate.opsForValue().set(getTokenKey(token), userDeviceTokenDTO, 30,
                    TimeUnit.DAYS);
        }

        tokenCache.setUid(userDeviceTokenDTO.getUid());
        if (userDeviceTokenDTO.getAccessExpiresIn() > System.currentTimeMillis()) {
            tokenCache.setLogin(true);
        }

        return tokenCache;
    }

    private UserDeviceTokenDTO getDeviceToken(String deviceId, String token) {
        UserDeviceTokenExample example = new UserDeviceTokenExample();
        example.or().andDeviceIdEqualTo(deviceId).andAccessTokenEqualTo(token);
        example.setOrderByClause("id desc limit 1");
        List<UserDeviceToken> deviceTokenList = userDeviceTokenMapper
                .selectByExample(example);
        if (CollectionUtils.isEmpty(deviceTokenList)) {
            return null;
        }
        UserDeviceTokenDTO dto = new UserDeviceTokenDTO();
        BeanUtils.copyProperties(deviceTokenList.get(0), dto);
        return dto;
    }

    @Override
    public UserDeviceTokenDTO refreshToken(@NotBlank @Min(1) Integer uid,
                                           @NotBlank @Size(min = 3, max = 50) String deviceId, @NotBlank Byte deviceType,
                                           @Size(min = 1, max = 100) String os, String refreshToken) {

        UserDeviceTokenExample example = new UserDeviceTokenExample();
        example.or().andUidEqualTo(uid).andDeviceIdEqualTo(deviceId)
                .andDeviceTypeEqualTo(deviceType).andRefreshTokenEqualTo(refreshToken);
        example.setOrderByClause("id desc limit 1");

        List<UserDeviceToken> deviceTokenList = userDeviceTokenMapper
                .selectByExample(example);
        if (CollectionUtils.isEmpty(deviceTokenList)) {
            throw new ServiceDataException(ResultJson.INVALID_PARAM,
                    "msg.user.refresh.invalid");
        }
        UserDeviceToken token = deviceTokenList.get(0);

        if (token.getRefreshExpiresIn() != null
                && token.getRefreshExpiresIn() > System.currentTimeMillis()) {
            return createToken(uid, deviceId, deviceType, os, true);
        }

        throw new ServiceDataException(ResultJson.INVALID_PARAM,
                "msg.user.refresh.invalid");
    }

    @Override
    public boolean logout(@NotBlank @Min(1) Integer uid,
                          @NotBlank @Size(min = 3, max = 50) String deviceId, @NotBlank Byte deviceType,
                          @Size(min = 1, max = 100) String os) {
        UserDeviceTokenExample example = new UserDeviceTokenExample();
        example.or().andUidEqualTo(uid).andDeviceIdEqualTo(deviceId)
                .andDeviceTypeEqualTo(deviceType);
        example.setOrderByClause("id desc limit 1");
        List<UserDeviceToken> deviceTokenList = userDeviceTokenMapper
                .selectByExample(example);
        if (CollectionUtils.isEmpty(deviceTokenList)) {
            return true;
        }
        UserDeviceToken token = deviceTokenList.get(0);

        redisTemplate.delete(getTokenKey(token.getAccessToken()));

        return userDeviceTokenMapper.deleteByPrimaryKey(token.getId()) > 0;
    }

    String getTokenKey(String token) {
        return String.format("%s%s", RedisConstants.TOKEN_PREFIX, token);
    }

}
