package com.little.g.springcloud.sample.service;

import com.little.g.springcloud.sample.api.EchoService;
import com.little.g.springcloud.sample.mapper.AiAssistantMapper;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;

@Service(protocol = "dubbo")
public class EchoServiceImpl implements EchoService {

	@Resource
	private AiAssistantMapper aiAssistantMapper;

	@Override
	public String echo(String message) {
		return null;
	}

}
