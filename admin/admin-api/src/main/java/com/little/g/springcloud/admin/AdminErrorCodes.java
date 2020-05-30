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

	public static final Integer ADMIN_INVALID_NAME = 20601;

	public static final Integer ADMIN_INVALID_PASSWORD = 20602;

	public static final Integer ADMIN_NAME_EXIST = 20602;

	public static final Integer ADMIN_ALTER_NOT_ALLOWED = 20603;

	public static final Integer ADMIN_DELETE_NOT_ALLOWED = 20604;

	public static final Integer ADMIN_INVALID_ACCOUNT = 20605;

	public static final Integer GOODS_UPDATE_NOT_ALLOWED = 20610;

	public static final Integer GOODS_NAME_EXIST = 20611;

	public static final Integer ORDER_CONFIRM_NOT_ALLOWED = 20620;

	public static final Integer ORDER_REFUND_FAILED = 20621;

	public static final Integer ORDER_REPLY_EXIST = 20622;

	public static final Integer ORDER_DELETE_FAILED = 20623;

	public static final Integer USER_INVALID_NAME = 20630;

	public static final Integer USER_INVALID_PASSWORD = 20631;

	public static final Integer USER_INVALID_MOBILE = 20632;

	public static final Integer USER_NAME_EXIST = 20633;

	public static final Integer USER_MOBILE_EXIST = 20634;

	public static final Integer ROLE_NAME_EXIST = 20640;

	public static final Integer ROLE_SUPER_SUPERMISSION = 20641;

	public static final Integer ROLE_USER_EXIST = 20642;

	public static final Integer GROUPON_GOODS_UNKNOWN = 20650;

	public static final Integer GROUPON_GOODS_EXISTED = 20651;

	public static final Integer GROUPON_GOODS_OFFLINE = 20652;

	public static final Integer NOTICE_UPDATE_NOT_ALLOWED = 20660;

	public static final Integer AFTERSALE_NOT_ALLOWED = 20670;

	public AdminErrorCodes() {
		super(ErrorCodeDiv.ADMIN);
	}

	@Override
	protected void addCodes() {
		addCode2Map(ERROR_LOGIN_REFUSED, "msg.user.login.refused");
		addCode2Map(ERR_NOT_ALLOW_OPERATION, "msg.user.privilege.need");
		addCode2Map(ERROR_CODE_LIMIT, "msg.user.sms.limit");
		addCode2Map(ERROR_AUDIT_FAIL, "msg.user.login.fail");
		addCode2Map(ADMIN_INVALID_NAME, "msg.admin.invalid.name");
		addCode2Map(ADMIN_INVALID_PASSWORD, "msg.admin.invalid.password");
		addCode2Map(ADMIN_NAME_EXIST, "msg.admin.name.exist");

	}

}
