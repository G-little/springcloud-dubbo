package com.little.g.springcloud.admin.web.interceptor;

import com.little.g.springcloud.admin.api.ResourcesService;
import com.little.g.springcloud.admin.enums.LogicalEnum;
import com.little.g.springcloud.admin.web.annotation.RequiresPermissions;
import com.little.g.springcloud.admin.web.utils.SessionUtils;
import com.little.g.springcloud.admin.web.vo.LoginSession;
import com.little.g.springcloud.common.error.CommonErrorCodes;
import com.little.g.springcloud.common.exception.ServiceDataException;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登陆权限认证拦截器
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    private static Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    @Reference
    private ResourcesService resourcesService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        LoginSession session = SessionUtils.getLoginSession(request);
        if (session == null) {
            throw new ServiceDataException(CommonErrorCodes.NOT_LOGIN);
        }
        HandlerMethod method = (HandlerMethod) handler;
        RequiresPermissions methodAnnotation = method.getMethodAnnotation(RequiresPermissions.class);
        //TODO 设置权限逻辑
        String[] permissions = null;
        LogicalEnum l = null;
        if (methodAnnotation != null) {
            permissions = methodAnnotation.value();
            l = methodAnnotation.logical();
            if (l == null) {
                l = LogicalEnum.AND;
            }
        }
        SessionUtils.set(session.getAdminUser());
        return resourcesService.hasPrivilege(permissions, l, request.getRequestURI(),
                session.getAdminUser());

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) throws Exception {

        super.afterCompletion(request, response, handler, ex);
    }

}
