package com.little.g.springcloud.admin.utils;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class AliyunSmsUtil {

    static DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou",
            "LTAI9rxb9Xna9L5x", "xxx");

    private static final Logger log = LoggerFactory.getLogger(AliyunSmsUtil.class);

    public static void sendSms(String mobile, String template,
                               Map<String, Object> params) {
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", mobile);
        request.putQueryParameter("SignName", "测试");
        request.putQueryParameter("TemplateCode", template);
        if (params != null && params.size() > 0) {
            request.putQueryParameter("TemplateParam", JSONObject.toJSONString(params));
        }
        try {
            CommonResponse response = client.getCommonResponse(request);
            log.info("send sms result:{}", response.getData());
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

}
