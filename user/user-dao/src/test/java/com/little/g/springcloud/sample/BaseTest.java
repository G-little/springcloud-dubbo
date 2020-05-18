package com.little.g.springcloud.sample;

import com.little.g.springcloud.sample.bootstrap.DubboDao;
import com.little.g.springcloud.sample.mapper.AiAssistantMapper;
import com.little.g.springcloud.sample.model.AiAssistant;
import com.little.g.springcloud.sample.model.AiAssistantExample;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DubboDao.class)
public class BaseTest {

	@Resource
	private AiAssistantMapper aiAssistantMapper;

	@Test
	public void testQuery() {
		AiAssistantExample example = new AiAssistantExample();
		List<AiAssistant> aiAssistants = aiAssistantMapper.selectByExample(example);
		System.out.println(aiAssistants);
	}

}
