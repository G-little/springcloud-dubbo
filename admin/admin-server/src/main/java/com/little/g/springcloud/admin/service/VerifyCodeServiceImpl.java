package com.little.g.springcloud.admin.service;

import com.little.g.springcloud.admin.AdminResultJson;
import com.little.g.springcloud.admin.api.VerifyCodeService;
import com.little.g.springcloud.common.exception.ServiceDataException;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.data.redis.core.ValueOperations;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.Objects;

/**
 * Created by llg on 2019/10/20.
 */
@Service(protocol = "dubbo")
public class VerifyCodeServiceImpl implements VerifyCodeService {

	@Resource
	private ValueOperations<String, String> valueOperations;

	private static String getCodeKey(Byte type, String mobile) {
		return String.format("ADMIN_CODE_%d_%s", type, mobile);
	}

	@Override
	public String sendCode(Byte type, String mobile) throws ServiceDataException {
		String key = getCodeKey(type, mobile);
		String code = valueOperations.get(key);
		if (StringUtils.isNotEmpty(code)) {
			throw new ServiceDataException(AdminResultJson.ERROR_CODE_LIMIT);
		}

		code = String.valueOf(RandomUtils.nextInt(100000, 999999));

		valueOperations.set(key, code, Duration.ofSeconds(60));
		return code;
	}

	@Override
	public boolean checkCode(Byte type, String mobile, String code) {
		String cacheCode = valueOperations.get(getCodeKey(type, mobile));
		return Objects.equals(code, cacheCode);
	}

}
