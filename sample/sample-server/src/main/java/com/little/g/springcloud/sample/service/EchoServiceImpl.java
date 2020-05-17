package com.little.g.springcloud.sample.service;

import com.little.g.springcloud.sample.api.EchoService;
import com.little.g.springcloud.sample.mapper.AiAssistantMapper;
import com.little.g.springcloud.sample.model.AiAssistantExample;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;

@Service(protocol = "dubbo")
public class EchoServiceImpl implements EchoService {

	@Resource
	private AiAssistantMapper aiAssistantMapper;

	@Override
	public String echo(String message) {
		return String.valueOf(aiAssistantMapper.countByExample(new AiAssistantExample()));
	}

}
