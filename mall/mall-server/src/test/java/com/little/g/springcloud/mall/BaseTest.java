package com.little.g.springcloud.mall;

import com.little.g.springcloud.mall.bootstrap.DubboSpringCloudProviderBootstrap;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DubboSpringCloudProviderBootstrap.class,
		properties = { "spring.profiles.active=nacos" })
public class BaseTest {

}
