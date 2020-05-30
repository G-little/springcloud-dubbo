package com.little.g.springcloud.common.web.exception;

import com.little.g.springcloud.common.ResultJson;
import com.little.g.springcloud.common.error.ErrorCodes;
import com.little.g.springcloud.common.exception.ServiceDataException;
import com.little.g.springcloud.common.web.config.MessageSourceUtil;
import com.little.g.springcloud.common.web.interceptor.HeaderParamsHolder;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.rpc.RpcException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @Resource
    private MessageSource messageSource;

    @Resource
    private MessageSourceUtil messageSourceUtil;

    private static final Logger log = LoggerFactory
            .getLogger(GlobalExceptionHandler.class);

    /**
     * 用于处理通用异常
     */
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public ResultJson bindException(BindException e) {
        BindingResult bindingResult = e.getBindingResult();

        StringBuilder sb = new StringBuilder();

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            sb.append(fieldError.getField() + fieldError.getDefaultMessage());
            sb.append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        ResultJson r = new ResultJson();
        r.setC(ResultJson.INVALID_PARAM);
        r.setM(sb.toString());
        return r;
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultJson jsonErrorHandler(HttpServletRequest req, Exception e) {
        ResultJson r = new ResultJson();
        r.setC(ResultJson.SYSTEM_UNKNOWN_EXCEPTION);
        if (e instanceof RpcException) {
            RpcException rpc = (RpcException) e;
            r.setC(rpc.getCode());
            r.setM(rpc.getMessage());
            log.error("Request RpcException url:{},e", req.getRequestURI(),
                    e.getMessage());
        } else if (e instanceof ServiceDataException) {
            ServiceDataException service = (ServiceDataException) e;
            r.setC(service.getCode());

            r.setM(messageSourceUtil.e2Msg(service));

            log.error("Request ServiceDataException url:{},e:{}", req.getRequestURI(),
                    e.getMessage());
        } else if (e instanceof ConstraintViolationException) {
            ConstraintViolationException violationException = (ConstraintViolationException) e;
            r.setC(ResultJson.INVALID_PARAM);
            if (!CollectionUtils.isEmpty(violationException.getConstraintViolations())) {
                StringBuilder sb = new StringBuilder();
                for (ConstraintViolation ce : violationException
                        .getConstraintViolations()) {
                    sb.append(ce.getPropertyPath());
                    sb.append(ce.getMessage());
                    sb.append(",");
                }
                sb.deleteCharAt(sb.length() - 1);
                r.setM(sb.toString());
            } else {
                r.setM(violationException.getMessage());
            }
        } else {
            r.setM(e.getMessage());
            if (StringUtils.isEmpty(r.getM())) {
                r.setM(messageSource.getMessage(ErrorCodes.getDefaultMsg(r.getC()),
                        null, HeaderParamsHolder.getHeader().getLocale()));
            }
            log.error("Request exception url:{},e", req.getRequestURI(), e);
        }
        return r;
    }

}
