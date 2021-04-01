package com.chinamobile.cmss.bcse.tool.tools;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import com.chinamobile.cmss.bcse.tool.config.Config;
import com.chinamobile.cmss.bcse.tool.exception.EmailException;
import com.chinamobile.cmss.bcse.tool.exception.ExceptionConstants;

import sun.tools.tree.ThisExpression;

/**
 * @author Quan
 * 
 */
public class MailUtil {
	public static void main(String[] args) throws Exception {

		Config.SERVER_HOST = "mail.smtp.host";
		Config.SERVER_URL = "smtp.139.com";
		Config.SERVER_AUTH = "mail.smtp.auth";
		Config.SEND__USER = "bc_se@139.com";
		Config.BCSE_INFO = "BCSE";
		Config.SEND__USER_NAME = "bc_se@139.com";
		Config.SEND__USER_PASSWORD = "123@bcse";

		sendEmail("624160412@qq.com", "12212");

	}

	/**
	 * 发送邮件接口
	 * 
	 * @param mailAddress
	 * @param msg
	 * @return
	 * @throws Exception
	 */
	public static void sendEmail(String mailAddress, String password) {

		// 配置文件对象
		Properties props = new Properties();
		// 邮箱服务地址
		props.put(Config.SERVER_HOST, Config.SERVER_URL);
		// 是否进行验证
		props.put(Config.SERVER_AUTH, "true");
		// 创建一个会话
		Session session = Session.getInstance(props);
		// 打开调试，会打印与邮箱服务器回话的内容
		session.setDebug(true);
		Message message = new MimeMessage(session);
		// 如果发送人没有写对，那么会出现 javamail 550 Invalid User
		// 如果发送人写的和使用的帐号不一致，则会出现 553 Mail from must equal authorized user
		InternetAddress from;

		try {
			from = new InternetAddress(Config.SEND__USER, "UTF-8", "B");
			Config.BCSE_INFO = new String(Config.BCSE_INFO.getBytes("ISO-8859-1"), "utf-8");
			from.setPersonal(MimeUtility.encodeText(Config.BCSE_INFO, "utf-8", "B"));
			message.setFrom(from);
			InternetAddress to = new InternetAddress(mailAddress);
			message.setRecipient(Message.RecipientType.TO, to);
			message.setSubject(MimeUtility.encodeText(Config.BCSE_INFO + Math.random()));
			// message.setText("用户名：" + mailAddress + "\n" + "密码：" + password);
			message.setContent("用户名：" + mailAddress + "\n" + "密码：" + password, "text/plain;charset=UTF-8"); // 就是这里…
			message.setSentDate(new Date());
			Transport transport = session.getTransport("smtp");
			// 具体你使用邮箱的smtp地址和端口，应该到邮箱里面查看，如果使用了SSL，网易的端口应该是 465/994
			transport.connect(Config.SERVER_URL, 25, Config.SEND__USER_NAME, Config.SEND__USER_PASSWORD);
			try {
				transport.sendMessage(message, message.getAllRecipients());
				LogUtil.loggerEntrance(LogUtil.WEB_LOG, "send once");
			} catch (Exception e) {
				Thread.sleep(3000);
				LogUtil.loggerEntrance(LogUtil.WEB_LOG, "send twice");
				transport.sendMessage(message, message.getAllRecipients());
				
			}
			transport.close();

		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.loggerEntrance(LogUtil.WEB_LOG, e.getMessage());
			throw new EmailException(ExceptionConstants.SqlOrDatabaseException);

		}

	}
}