package com.little.g.springcloud.common.error;

/**
 * Created by lengligang on 2019/3/12.
 */
public class CommonErrorCodes extends ErrorCodes {

	public static final Integer INVALID_PARAM = 401; // 参数不对

	public static final Integer INVALID_VALUE = 402; // 参数值不对

	public static final Integer NOT_LOGIN = 501; // 未登录，请先登录！

	public static final Integer SYSTEM_UNKNOWN_EXCEPTION = 502; // 系统异常

	public static final Integer BUSINESS_UNSUPPORT_ERROR = 503; // 业务不支持

	public static final Integer UPDATE_DATA_TIMEOUT_ERROR = 504; // 更新数据已失效

	public static final Integer UPDATE_DATA_FAILED_ERROR = 505; // 更新数据已失效

	public static final Integer NO_PRIVILEGE_ERROR = 506; // 没有操作权限

	public static final Integer SYSTEM_LIMIT = 10003;

	public static final Integer SYSTEM_EXCEPTION = 10005;

	public CommonErrorCodes() {
		super(ErrorCodeDiv.COMMON);
	}

	@Override
	protected void addCodes() {

		addCode2Map(INVALID_PARAM, "msg.param.invalid");
		addCode2Map(INVALID_VALUE, "msg.value.invalid");
		addCode2Map(NOT_LOGIN, "msg.user.login.need");
		addCode2Map(SYSTEM_UNKNOWN_EXCEPTION, "msg.system.unknow");
		addCode2Map(BUSINESS_UNSUPPORT_ERROR, "msg.business.unsupport");
		addCode2Map(UPDATE_DATA_TIMEOUT_ERROR, "msg.update.data.timeout");
		addCode2Map(UPDATE_DATA_FAILED_ERROR, "msg.update.data.failed");
		addCode2Map(NO_PRIVILEGE_ERROR, "msg.no.privilege");
	}

}
