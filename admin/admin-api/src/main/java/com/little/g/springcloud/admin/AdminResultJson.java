package com.little.g.springcloud.admin;

import com.little.g.springcloud.common.ResultJson;

/**
 * Created by llg on 2019/12/22.
 */
public class AdminResultJson extends ResultJson {

	public static final Integer ERROR_LOGIN_REFUSED = 20022;// 禁止登陆

	public static final Integer ERROR_NEET_LOGIN = 20020; // 未登录，请先登录！

	public static final Integer ERR_NOT_ALLOW_OPERATION = 20238;// 无此操作权限

	public static final Integer ERROR_CODE_LIMIT = 20023;// 短信限制

	public static final Integer ERROR_AUDIT_FAIL = 20021;// 登陆认证失败

}
