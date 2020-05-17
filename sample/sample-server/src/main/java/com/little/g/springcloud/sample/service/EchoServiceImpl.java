package com.little.g.springcloud.sample.service;

import com.little.g.springcloud.sample.api.EchoService;
import org.apache.dubbo.config.annotation.Service;

@Service(protocol = "dubbo")
public class EchoServiceImpl implements EchoService {

	@Override
	public String echo(String message) {
		return null;
	}

}
