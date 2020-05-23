package com.little.g.springcloud.admin.api;

import com.little.g.springcloud.common.exception.ServiceDataException;

/**
 * Created by llg on 2019/10/20.
 */
public interface VerifyCodeService {

	/**
	 * 发送验证码
	 * @param type
	 * @param mobile
	 * @return
	 */
	String sendCode(Byte type, String mobile) throws ServiceDataException;

	/**
	 * 验证
	 * @param type
	 * @param mobile
	 * @return
	 */
	boolean checkCode(Byte type, String mobile, String code);

}
