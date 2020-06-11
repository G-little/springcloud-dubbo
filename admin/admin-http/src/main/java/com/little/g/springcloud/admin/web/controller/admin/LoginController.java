package com.little.g.springcloud.admin.web.controller.admin;

import com.little.g.springcloud.admin.AdminResultJson;
import com.little.g.springcloud.admin.api.AdminUserService;
import com.little.g.springcloud.admin.api.ResourcesService;
import com.little.g.springcloud.admin.dto.AdminUserDTO;
import com.little.g.springcloud.admin.dto.UserResourceDTO;
import com.little.g.springcloud.admin.web.form.LoginForm;
import com.little.g.springcloud.admin.web.utils.SessionUtils;
import com.little.g.springcloud.admin.web.vo.LoginSession;
import com.little.g.springcloud.common.ResultJson;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

/**
 * Created by llg on 2019/10/20.
 */
@RestController
public class LoginController {

	@Reference
	private AdminUserService adminUserService;

	@Reference
	private ResourcesService resourcesService;

	@RequestMapping("/logout")
	public ResultJson logout(HttpServletRequest request) {
		ResultJson r = new ResultJson();
		SessionUtils.logout(request);
		return r;

	}

	@RequestMapping("/login")
	public ResultJson login(@Valid LoginForm login, HttpServletRequest request) {

		ResultJson result = new ResultJson();

		AdminUserDTO admin = adminUserService.login(login.getMobile(),
				login.getPassword(), login.getSmscode());
		if (admin == null) {
			result.setC(AdminResultJson.ERROR_AUDIT_FAIL);
			result.setM("登陆验证失败!");
			return result;
		}

		if (SessionUtils.getLoginSession(request) != null) {
			LoginSession session = SessionUtils.getLoginSession(request);
			AdminUserDTO adminSession = session.getAdminUser();
			if (adminSession != null
					&& Objects.equals(adminSession.getId(), admin.getId())) {
				List<UserResourceDTO> menuList = resourcesService
						.userInitMenu(admin.getId());
				result.putD("menus", menuList);
				result.putD("user", admin);
				return result;
			}
		}

		LoginSession session = new LoginSession();
		session.setAdminUser(admin);
		SessionUtils.setSession(request, session);

		// TODO 返回登陆菜单及用户信息
		List<UserResourceDTO> menuList = resourcesService.userInitMenu(admin.getId());
		result.putD("menus", menuList);
		result.putD("user", admin);
		return result;
	}

	@RequestMapping("/login_code")
	public ResultJson sendLoginCode(@RequestParam String mobile) {
		ResultJson result = new ResultJson();
		String code = adminUserService.sendLoginCode(mobile);
		if (StringUtils.isNotEmpty(code)) {
			result.putD("smscode", code);
		}
		return result;
	}

}
