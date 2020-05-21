package com.little.g.springcloud.user.service;

import com.little.g.springcloud.user.api.RateLimitService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * Created by lengligang on 2018/7/14.
 */
@Service("rateLimitService")
public class RateLimitServiceImpl implements RateLimitService {

	@Resource
	private RedisTemplate redisTemplate;

	@Override
	public boolean upLimit(String key, long max, long seconds) {

		ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
		String timeKey = key + "_time";
		String initTime = valueOperations.get(timeKey);
		if (StringUtils.isEmpty(initTime) || "null".equals(initTime)) {
			valueOperations.set(timeKey, String.valueOf(System.currentTimeMillis()),
					seconds, TimeUnit.SECONDS);
			redisTemplate.delete(key);
			return false;
		}
		else {
			Long initTimeLong = Long.valueOf(initTime);
			if ((System.currentTimeMillis() - initTimeLong) / 1000 >= seconds) {
				valueOperations.set(timeKey, String.valueOf(System.currentTimeMillis()),
						seconds, TimeUnit.SECONDS);
				redisTemplate.delete(key);
				return false;
			}

			Long counter = valueOperations.increment(key);
			if (counter >= max) {
				return true;
			}

		}

		return false;
	}

	@Override
	public Integer getCurrent(String key) {
		ValueOperations<String, Integer> valueOperations = redisTemplate.opsForValue();
		Integer count = valueOperations.get(key);
		return count == null ? 0 : count;
	}

}
