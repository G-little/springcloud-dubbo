package com.little.g.springcloud.mall.notify;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import com.little.g.springcloud.mall.dto.SmsResultDTO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;

/*
 * 腾讯云短信服务
 */
public class TencentSmsSender implements SmsSender {

    private final Log logger = LogFactory.getLog(TencentSmsSender.class);

    private SmsSingleSender sender;

    private String sign;

    public SmsSingleSender getSender() {
        return sender;
    }

    public void setSender(SmsSingleSender sender) {
        this.sender = sender;
    }

    @Override
    public SmsResultDTO send(String phone, String content) {
        try {
            SmsSingleSenderResult result = sender.send(0, "86", phone, content, "", "");
            logger.debug(result);

            SmsResultDTO smsResult = new SmsResultDTO();
            smsResult.setSuccessful(true);
            smsResult.setResult(result);
            return smsResult;
        } catch (HTTPException | IOException e) {
            logger.error(e.getMessage(), e);
        }

        SmsResultDTO smsResult = new SmsResultDTO();
        smsResult.setSuccessful(false);
        return smsResult;
    }

    @Override
    public SmsResultDTO sendWithTemplate(String phone, String templateId,
                                         String[] params) {
        try {
            SmsSingleSenderResult result = sender.sendWithParam("86", phone,
                    Integer.parseInt(templateId), params, this.sign, "", "");
            logger.debug(result);

            SmsResultDTO smsResult = new SmsResultDTO();
            smsResult.setSuccessful(true);
            smsResult.setResult(result);
            return smsResult;
        } catch (HTTPException | IOException e) {
            logger.error(e.getMessage(), e);
        }

        SmsResultDTO smsResult = new SmsResultDTO();
        smsResult.setSuccessful(false);
        return smsResult;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

}
