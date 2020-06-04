package com.little.g.springcloud.pay;

import com.little.g.springcloud.bootstrap.DubboSpringCloudProviderBootstrap;
import com.little.g.springcloud.pay.api.UserAccountService;
import com.little.g.springcloud.pay.dto.UserAccountDTO;
import org.apache.dubbo.config.annotation.Reference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DubboSpringCloudProviderBootstrap.class,
		properties = "spring.profiles.active=nacos")
public class BaseTest {

	@Reference
	private UserAccountService userAccountService;

	@Test
	public void testQuery() {
		UserAccountDTO userAccountDTO = userAccountService.queryUserAccount(1);
		System.out.println(userAccountDTO);
	}

}
