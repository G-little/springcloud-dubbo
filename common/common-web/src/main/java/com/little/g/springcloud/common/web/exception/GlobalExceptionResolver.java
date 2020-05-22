package com.little.g.springcloud.common.web.exception;

import com.little.g.springcloud.common.ResultJson;
import com.little.g.springcloud.common.exception.ServiceDataException;
import com.little.g.springcloud.common.web.config.MessageSourceUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Slf4j
public class GlobalExceptionResolver implements HandlerExceptionResolver {

	@Resource
	private MessageSourceUtil messageSourceUtil;

	@Override
	public ModelAndView resolveException(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, Object o, Exception e) {
		if (e instanceof ServiceDataException) {
			ServiceDataException s = (ServiceDataException) e;
			ResultJson r = new ResultJson();
			r.setC(s.getCode());
			r.setM(messageSourceUtil.e2Msg(s));
			ModelAndView m = new ModelAndView(new MappingJackson2JsonView());
			m.addObject(r);

			log.error("Request ServiceDataException url:{},e:{}", httpServletRequest.getRequestURI(),
					e.getMessage());
			return m;
		}

		return null;
	}

}
