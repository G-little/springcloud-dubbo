package com.little.g.springcloud.mall.config;

import com.little.g.springcloud.mall.api.StorageService;
import com.little.g.springcloud.mall.service.StorageServiceImpl;
import com.little.g.springcloud.mall.storage.*;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(StorageProperties.class)
public class StorageAutoConfiguration {

}
