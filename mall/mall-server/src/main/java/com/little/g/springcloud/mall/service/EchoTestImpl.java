package com.little.g.springcloud.mall.service;

import com.little.g.springcloud.mall.api.EchoTest;
import org.apache.dubbo.config.annotation.Service;

@Service(protocol = "dubbo")
public class EchoTestImpl implements EchoTest {

	@Override
	public String testEcho(String echo) {
		return echo;
	}

}
