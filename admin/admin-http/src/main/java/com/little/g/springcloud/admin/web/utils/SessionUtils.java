package com.little.g.springcloud.admin.web.utils;

import com.little.g.springcloud.admin.dto.AdminUserDTO;
import com.little.g.springcloud.admin.web.vo.LoginSession;
import org.springframework.core.NamedThreadLocal;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by llg on 2019/10/20.
 */
public class SessionUtils {

	private static final String LOGIN_SESSION_KEY = "_ADMIN_SESSION";

	private static ThreadLocal<AdminUserDTO> context = new NamedThreadLocal<>(
			"session context");

	public static void set(AdminUserDTO user) {
		context.set(user);
	}

	public static AdminUserDTO get() {
		return context.get();
	}

	public static LoginSession getLoginSession(HttpServletRequest request) {
		Object loginObject = request.getSession().getAttribute(LOGIN_SESSION_KEY);
		if (loginObject == null)
			return null;
		return (LoginSession) loginObject;
	}

	public static void setSession(HttpServletRequest request, LoginSession session) {
		request.getSession().setAttribute(LOGIN_SESSION_KEY, session);
	}

	public static AdminUserDTO getAdminUser(HttpServletRequest request) {
		LoginSession session = getLoginSession(request);
		if (session == null)
			return null;
		return session.getAdminUser();
	}

	public static Integer getAdminId(HttpServletRequest request) {
		LoginSession session = getLoginSession(request);
		if (session != null) {
			AdminUserDTO admin = session.getAdminUser();
			if (admin != null) {
				return admin.getId();
			}
		}
		return null;
	}

	public static Integer getRoleId(HttpServletRequest request) {
		LoginSession session = getLoginSession(request);
		if (session != null) {
			AdminUserDTO admin = session.getAdminUser();
			if (admin != null) {
				return admin.getRoleId();
			}
		}
		return null;
	}

	public static void logout(HttpServletRequest request) {
		request.getSession().removeAttribute(LOGIN_SESSION_KEY);
	}

}
