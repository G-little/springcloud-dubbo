package com.little.g.springcloud.common.web.config;

import com.little.g.springcloud.common.error.ErrorCodes;
import com.little.g.springcloud.common.exception.ServiceDataException;
import com.little.g.springcloud.common.web.interceptor.HeaderParamsHolder;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;

/**
 * Created by lengligang on 2019/3/13.
 */
public class MessageSourceUtil {

	private static final Logger log = LoggerFactory.getLogger(MessageSourceUtil.class);

	@Autowired
	private MessageSource messageSource;

	/**
	 * 国际化
	 * @param result
	 * @return
	 */
	public String getMessage(String result, Object[] params) {
		String message = "";
		try {
			Locale locale = LocaleContextHolder.getLocale();
			message = messageSource.getMessage(result, params, locale);
		}
		catch (Exception e) {
			log.error("parse message error! ", e);
		}
		return message;
	}

	public String e2Msg(ServiceDataException e) {

		if (!StringUtils.isEmpty(e.getMessage())
				&& !StringUtils.equals(e.getMessage(), String.valueOf(e.getCode()))) {
			if (e.getMessage().startsWith("msg.")) {
				try {
					return messageSource.getMessage(e.getMessage(), null,
							HeaderParamsHolder.getHeader().getLocale());
				}
				catch (Exception ex) {
					log.error("message source get message failed code:{},local:{}",
							e.getMessage(), HeaderParamsHolder.getHeader().getLocale());
				}
			}
		}
		else {
			String msg = "";
			if (e.getCode() > 0) {
				msg = messageSource.getMessage(ErrorCodes.getDefaultMsg(e.getCode()),
						null, HeaderParamsHolder.getHeader().getLocale());
			}
			if (StringUtils.isEmpty(msg)) {
				msg = e.getMessage();
			}
			return msg;
		}
		return "";
	}

}
