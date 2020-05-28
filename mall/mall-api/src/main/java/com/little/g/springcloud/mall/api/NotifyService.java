package com.little.g.springcloud.mall.api;

import com.little.g.springcloud.mall.dto.SmsResultDTO;
import com.little.g.springcloud.mall.enums.NotifyType;

public interface NotifyService {

    void notifySms(String phoneNumber, String message);

    void notifySmsTemplate(String phoneNumber, NotifyType notifyType, String[] params);

    SmsResultDTO notifySmsTemplateSync(String phoneNumber, NotifyType notifyType,
                                       String[] params);

    void notifyMail(String subject, String content);

}
