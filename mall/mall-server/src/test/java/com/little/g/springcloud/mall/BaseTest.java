package com.little.g.springcloud.mall;

import com.little.g.springcloud.mall.api.LitemallUserService;
import com.little.g.springcloud.mall.bootstrap.DubboSpringCloudProviderBootstrap;
import com.little.g.springcloud.mall.dto.LitemallUserDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DubboSpringCloudProviderBootstrap.class,
        properties = {"spring.profiles.active=nacos"})
public class BaseTest {

	@Resource
	private LitemallUserService litemallUserService;
	@Test
	public void testGetConfig() {
		List<LitemallUserDTO> litemallUserDTOS = litemallUserService.queryByMobile("15201008961");
		System.out.println(litemallUserDTOS);
	}

}
