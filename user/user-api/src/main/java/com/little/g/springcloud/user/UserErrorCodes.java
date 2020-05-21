package com.little.g.springcloud.user;

import com.little.g.springcloud.common.error.ErrorCodeDiv;
import com.little.g.springcloud.common.error.ErrorCodes;

/**
 * Created by lengligang on 2019/3/12.
 */
public class UserErrorCodes extends ErrorCodes {

	public static final Integer USER_ERROR = 30000;

	public static final Integer USER_NOT_EXIST = 30001;

	public UserErrorCodes() {
		super(ErrorCodeDiv.USER);
	}

	@Override
	protected void addCodes() {
		addCode2Map(USER_ERROR, "msg.user.error");
		addCode2Map(USER_NOT_EXIST, "msg.user.not.exist");

	}

}
