package com.little.g.springcloud.mall.service;

import com.github.qcloudsms.SmsSingleSender;
import com.little.g.springcloud.mall.api.NotifyService;
import com.little.g.springcloud.mall.config.NotifyProperties;
import com.little.g.springcloud.mall.dto.SmsResultDTO;
import com.little.g.springcloud.mall.enums.NotifyType;
import com.little.g.springcloud.mall.notify.AliyunSmsSender;
import com.little.g.springcloud.mall.notify.SmsSender;
import com.little.g.springcloud.mall.notify.TencentSmsSender;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Async;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * 商城通知服务类
 */
@Service(protocol = "dubbo")
public class NotifyServiceImpl implements NotifyService {

	@Resource
	private NotifyProperties properties;

	private MailSender mailSender;

	private String sendFrom;

	private String sendTo;

	private SmsSender smsSender;

	private List<Map<String, String>> smsTemplate = new ArrayList<>();

	private List<Map<String, String>> wxTemplate = new ArrayList<>();

	public boolean isMailEnable() {
		return mailSender != null;
	}

	public boolean isSmsEnable() {
		return smsSender != null;
	}

	@PostConstruct
	public void init() {
		NotifyProperties.Mail mailConfig = properties.getMail();
		if (mailConfig.isEnable()) {
			setMailSender(mailSender());
			setSendFrom(mailConfig.getSendfrom());
			setSendTo(mailConfig.getSendto());
		}

		NotifyProperties.Sms smsConfig = properties.getSms();
		if (smsConfig.isEnable()) {
			if (smsConfig.getActive().equals("tencent")) {
				setSmsSender(tencentSmsSender());
			}
			else if (smsConfig.getActive().equals("aliyun")) {
				setSmsSender(aliyunSmsSender());
			}

			setSmsTemplate(smsConfig.getTemplate());
		}
	}

	public JavaMailSender mailSender() {
		NotifyProperties.Mail mailConfig = properties.getMail();
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost(mailConfig.getHost());
		mailSender.setUsername(mailConfig.getUsername());
		mailSender.setPassword(mailConfig.getPassword());
		mailSender.setPort(mailConfig.getPort());
		Properties properties = new Properties();
		properties.put("mail.smtp.auth", true);
		properties.put("mail.smtp.timeout", 5000);
		properties.put("mail.smtp.starttls.enable", true);
		properties.put("mail.smtp.socketFactory.fallback", "false");
		// 阿里云 必须加入配置 outlook配置又不需要 视情况而定.发送不成功多数是这里的配置问题
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		properties.put("mail.smtp.socketFactory.port", mailConfig.getPort());
		properties.put("debug", true);
		mailSender.setJavaMailProperties(properties);
		return mailSender;
	}

	public TencentSmsSender tencentSmsSender() {
		NotifyProperties.Sms smsConfig = properties.getSms();
		TencentSmsSender smsSender = new TencentSmsSender();
		NotifyProperties.Sms.Tencent tencent = smsConfig.getTencent();
		smsSender.setSender(new SmsSingleSender(tencent.getAppid(), tencent.getAppkey()));
		smsSender.setSign(smsConfig.getSign());
		return smsSender;
	}

	public AliyunSmsSender aliyunSmsSender() {
		NotifyProperties.Sms smsConfig = properties.getSms();
		AliyunSmsSender smsSender = new AliyunSmsSender();
		NotifyProperties.Sms.Aliyun aliyun = smsConfig.getAliyun();
		smsSender.setSign(smsConfig.getSign());
		smsSender.setRegionId(aliyun.getRegionId());
		smsSender.setAccessKeyId(aliyun.getAccessKeyId());
		smsSender.setAccessKeySecret(aliyun.getAccessKeySecret());
		return smsSender;
	}

	/**
	 * 短信消息通知
	 * @param phoneNumber 接收通知的电话号码
	 * @param message 短消息内容，这里短消息内容必须已经在短信平台审核通过
	 */
	@Override
	@Async
	public void notifySms(String phoneNumber, String message) {
		if (smsSender == null) {
			return;
		}

		smsSender.send(phoneNumber, message);
	}

	/**
	 * 短信模版消息通知
	 * @param phoneNumber 接收通知的电话号码
	 * @param notifyType 通知类别，通过该枚举值在配置文件中获取相应的模版ID
	 * @param params 通知模版内容里的参数，类似"您的验证码为{1}"中{1}的值
	 */
	@Override
	@Async
	public void notifySmsTemplate(String phoneNumber, NotifyType notifyType,
			String[] params) {
		if (smsSender == null) {
			return;
		}

		String templateIdStr = getTemplateId(notifyType, smsTemplate);
		if (templateIdStr == null) {
			return;
		}

		smsSender.sendWithTemplate(phoneNumber, templateIdStr, params);
	}

	/**
	 * 以同步的方式发送短信模版消息通知
	 * @param phoneNumber 接收通知的电话号码
	 * @param notifyType 通知类别，通过该枚举值在配置文件中获取相应的模版ID
	 * @param params 通知模版内容里的参数，类似"您的验证码为{1}"中{1}的值
	 * @return
	 */
	@Override
	public SmsResultDTO notifySmsTemplateSync(String phoneNumber, NotifyType notifyType,
			String[] params) {
		if (smsSender == null) {
			return null;
		}

		return smsSender.sendWithTemplate(phoneNumber,
				getTemplateId(notifyType, smsTemplate), params);
	}

	/**
	 * 邮件消息通知, 接收者在spring.mail.sendto中指定
	 * @param subject 邮件标题
	 * @param content 邮件内容
	 */
	@Override
	@Async
	public void notifyMail(String subject, String content) {
		if (mailSender == null) {
			return;
		}

		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(sendFrom);
		message.setTo(sendTo);
		message.setSubject(subject);
		message.setText(content);
		mailSender.send(message);
	}

	private String getTemplateId(NotifyType notifyType,
			List<Map<String, String>> values) {
		for (Map<String, String> item : values) {
			String notifyTypeStr = notifyType.getType();

			if (item.get("name").equals(notifyTypeStr)) {
				return item.get("templateId");
			}
		}
		return null;
	}

	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void setSendFrom(String sendFrom) {
		this.sendFrom = sendFrom;
	}

	public void setSendTo(String sendTo) {
		this.sendTo = sendTo;
	}

	public void setSmsSender(SmsSender smsSender) {
		this.smsSender = smsSender;
	}

	public void setSmsTemplate(List<Map<String, String>> smsTemplate) {
		this.smsTemplate = smsTemplate;
	}

	public void setWxTemplate(List<Map<String, String>> wxTemplate) {
		this.wxTemplate = wxTemplate;
	}

}
