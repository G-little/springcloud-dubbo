package com.little.g.springcloud.bootstrap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DubboSpringCloudProviderBootstrap.class,
		properties = "spring.profiles.active=nacos")
public class BaseTest {

	@Resource
	DubboSpringCloudProviderBootstrap.TestJavaConfigBean testJavaConfigBean;

	@Test
	public void testGetConfig() {
		int batch = testJavaConfigBean.getBatch();
		System.out.println(batch);
	}

}
