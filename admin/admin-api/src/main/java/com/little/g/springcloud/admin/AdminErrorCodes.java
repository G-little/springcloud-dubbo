package com.little.g.springcloud.admin;

import com.little.g.springcloud.common.error.ErrorCodeDiv;
import com.little.g.springcloud.common.error.ErrorCodes;

/**
 * Created by lengligang on 2019/3/12.
 */
public class AdminErrorCodes extends ErrorCodes {

	public static final Integer ERROR_LOGIN_REFUSED = 20022;// 禁止登陆

	public static final Integer ERR_NOT_ALLOW_OPERATION = 20238;// 无此操作权限

	public static final Integer ERROR_CODE_LIMIT = 20023;// 短信限制

	public static final Integer ERROR_AUDIT_FAIL = 20021;// 登陆认证失败

	public AdminErrorCodes() {
		super(ErrorCodeDiv.ADMIN);
	}

	@Override
	protected void addCodes() {
		addCode2Map(ERROR_LOGIN_REFUSED, "msg.user.login.refused");
		addCode2Map(ERR_NOT_ALLOW_OPERATION, "msg.user.privilege.need");
		addCode2Map(ERROR_CODE_LIMIT, "msg.user.sms.limit");
		addCode2Map(ERROR_AUDIT_FAIL, "msg.user.login.fail");

	}

}
