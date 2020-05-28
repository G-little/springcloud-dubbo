package com.little.g.springcloud.mall.notify;

import com.little.g.springcloud.mall.dto.SmsResultDTO;

public interface SmsSender {

    /**
     * 发送短信息
     *
     * @param phone   接收通知的电话号码
     * @param content 短消息内容
     */
    SmsResultDTO send(String phone, String content);

    /**
     * 通过短信模版发送短信息
     *
     * @param phone      接收通知的电话号码
     * @param templateId 通知模板ID
     * @param params     通知模版内容里的参数，类似"您的验证码为{1}"中{1}的值
     */
    SmsResultDTO sendWithTemplate(String phone, String templateId, String[] params);

}
