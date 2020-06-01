package com.little.g.springcloud.admin.web.task;

import com.little.g.springcloud.common.task.TaskService;
import com.little.g.springcloud.mall.api.LitemallGrouponRulesService;
import com.little.g.springcloud.mall.dto.LitemallGrouponRulesDTO;
import com.little.g.springcloud.mall.util.GrouponConstant;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Component
public class AdminTaskStartupRunner implements ApplicationRunner {

	@Reference
	private LitemallGrouponRulesService rulesService;

	@Autowired
	private TaskService taskService;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		List<LitemallGrouponRulesDTO> grouponRulesList = rulesService
				.queryByStatus(GrouponConstant.RULE_STATUS_ON);
		if (CollectionUtils.isEmpty(grouponRulesList)) {
			return;
		}
		for (LitemallGrouponRulesDTO grouponRules : grouponRulesList) {
			LocalDateTime now = LocalDateTime.now();
			LocalDateTime expire = grouponRules.getExpireTime();
			if (expire.isBefore(now)) {
				// 已经过期，则加入延迟队列
				taskService.addTask(new GrouponRuleExpiredTask(grouponRules.getId(), 0));
			}
			else {
				// 还没过期，则加入延迟队列
				long delay = ChronoUnit.MILLIS.between(now, expire);
				taskService
						.addTask(new GrouponRuleExpiredTask(grouponRules.getId(), delay));
			}
		}
	}

}
