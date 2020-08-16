/*
 * 文  件  名:  MailUtils.java
 * 版        权:  Copyright 2016-2030 SZLY Group  All rights reserved
 * 描        述:  描述
 * 创  建  人:  ly-lijinzhong
 * 创建时间:  2018年4月12日
 * 修改内容:  修改内容
 */
package jum.utility;

import com.ly.mp.component.entities.MimeMailEntity;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Properties;

/**
 * 功能简述:<br>
 * 详细描述:<br>
 * 
 * @author 李锦忠, 2018年4月12日
 * @see 相关类#方法
 * @since 产品/模块
 */
public class MailUtils {

	/** 
	 * 功能简述:邮件发送帮助类<br>
	 * 详细描述:<br>
	 * @author 李锦忠
	 * @CreateTime 2018年4月12日下午5:09:32
	 * @param mme
	 * @return
	 * @throws Exception
	 */
	public static boolean send(MimeMailEntity mme) throws Exception {
		JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();
		senderImpl.setHost(mme.getSmtpHost());
		MimeMessage mailMessage = senderImpl.createMimeMessage();
		MimeMessageHelper messageHelper = null;
		messageHelper = new MimeMessageHelper(mailMessage, true, "utf-8");
		messageHelper.setTo(mme.getDest());
		messageHelper.setFrom(new InternetAddress(mme.getFrom(), mme.getUsername()));
		messageHelper.setSubject(mme.getSubject());
		String str1 = mme.getType();
		if (str1.equals("MIME_SIMPLE_TEXT")) {
			messageHelper.setText(mme.getText(),true);
		} else if (str1.equals("MIME_HTML_WITHOUT_ATTACHED")) {
			messageHelper.setText(mme.getText());
		} else if (str1.equals("MIME_HTML_SINGLE_IMAGE")) {
			messageHelper.setText("<html><head></head><body><img src=\"cid:pic\"/></body></html>", true);
			FileSystemResource img = new FileSystemResource(new File(mme.getImage()));
			messageHelper.addInline("_MAIL_PIC", img);
		} else if (str1.equals("MIME_ATTACHED_FILE")) {
			if (StringUtils.isNotBlank(mme.getHtml()))
				messageHelper.setText(mme.getHtml(), true);
			else if (StringUtils.isNotBlank(mme.getText())) {
				messageHelper.setText(mme.getText());
			}
			String filename = mme.getAttached();
			File diskFile = new File(filename);
			if ((diskFile.exists()) && (diskFile.length() <= 5242880L)) {
				FileSystemResource file = new FileSystemResource(diskFile);
				messageHelper.addAttachment(diskFile.getName(), file);
			} else {
				throw new Exception("error: attached file [" + filename + "] length out of round");
			}
		} else {
			messageHelper.setText(mme.getText());
		}

		senderImpl.setUsername(mme.getUsername());
		senderImpl.setPassword(mme.getPassword());
		Properties prop = new Properties();
		prop.put("mail.smtp.auth", mme.getIsauth());
		prop.put("mail.smtp.timeout", mme.getTimeout());
		senderImpl.setJavaMailProperties(prop);
		senderImpl.send(mailMessage);
		return true;
	}
}