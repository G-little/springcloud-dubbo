package com.little.g.springcloud.common.error;

/**
 * Created by lengligang on 2019/3/12.
 */
public class CommonErrorCodes extends ErrorCodes {

	public static final Integer SYSTEM_UNKNOWN_EXCEPTION = 10001;

	public static final Integer INVALID_PARAM = 10002;

	public static final Integer SYSTEM_LIMIT = 10003;

	public static final Integer SYSTEM_EXCEPTION = 10005;

	public static final Integer NOT_LOGIN = 10010; // 未登录，请先登录！

	public CommonErrorCodes() {
		super(ErrorCodeDiv.COMMON);
	}

	@Override
	protected void addCodes() {

		addCode2Map(SYSTEM_UNKNOWN_EXCEPTION, "msg.system.unknow");
		addCode2Map(INVALID_PARAM, "msg.param.invalid");
		addCode2Map(NOT_LOGIN, "msg.user.login.need");
	}

}
