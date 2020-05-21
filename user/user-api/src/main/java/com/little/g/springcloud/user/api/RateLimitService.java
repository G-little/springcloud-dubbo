package com.little.g.springcloud.user.api;

/**
 * Created by lengligang on 2018/7/14.
 */
public interface RateLimitService {

	/**
	 * 判断是否达到上限
	 * @param key
	 * @param max
	 * @return 是否达到上限
	 */
	boolean upLimit(String key, long max, long seconds);

	/**
	 * 获取当前次数
	 * @param key
	 * @return
	 */
	Integer getCurrent(String key);

}
