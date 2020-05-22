package com.little.g.springcloud.common.web.annotation;

import com.little.g.springcloud.common.web.config.AppConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(AppConfig.class)
public @interface EnableCmdErrorMsg {

}
