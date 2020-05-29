package com.little.g.springcloud.mall.web.task;

import com.little.g.springcloud.mall.api.LitemallOrderService;
import com.little.g.springcloud.mall.dto.LitemallOrderDTO;
import com.little.g.springcloud.mall.system.SystemConfig;
import com.little.g.springcloud.mall.web.manager.TaskService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Component
public class TaskStartupRunner implements ApplicationRunner {

    @Reference
    private LitemallOrderService orderService;

    @Resource
    private TaskService taskService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<LitemallOrderDTO> orderList = orderService
                .queryUnpaid(SystemConfig.getOrderUnpaid());
        for (LitemallOrderDTO order : orderList) {
            LocalDateTime add = order.getAddTime();
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime expire = add.plusMinutes(SystemConfig.getOrderUnpaid());
            if (expire.isBefore(now)) {
                // 已经过期，则加入延迟队列
                taskService.addTask(new OrderUnpaidTask(order.getId(), 0));
            } else {
                // 还没过期，则加入延迟队列
                long delay = ChronoUnit.MILLIS.between(now, expire);
                taskService.addTask(new OrderUnpaidTask(order.getId(), delay));
            }
        }
    }

}
