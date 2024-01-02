package com.co.jp.liushuang.utils;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.co.jp.liushuang.common.Constants;
import com.co.jp.liushuang.common.SysConstant;

/**
 * apache commons mail
 * http://commons.apache.org/proper/commons-email/
 *
 * @author　劉双
 */
public class MailUtil {

    private static final String HOSTNAME = PropertiesUtil.getEmailValue(Constants.HOSTNAME);
    private static final String POP_USERNAME = PropertiesUtil.getEmailValue(Constants.POP_USERNAME);
    private static final String USERNAME = PropertiesUtil.getEmailValue(Constants.USERNAME);
    private static final String POP_PASSWORD = PropertiesUtil.getEmailValue(Constants.POP_PASSWORD);
    private static final String CODING = PropertiesUtil.getEmailValue(Constants.CODING);
    private static final int PORT = Integer.parseInt(PropertiesUtil.getEmailValue(Constants.PORT));

    private static final Logger log = LoggerFactory.getLogger(MailUtil.class);

    //利用方法
    public static void main(String[] args) {

        String message = "お疲れ様です、@@Name@@です。\r\n" + "はは、毎日お仕事お疲れ様です、頑張ってね"
                + "\n\r 以上です、よろしくお願いいたします。";

        message = message.replaceAll("@@Name@@", "liu shuang");

        //s-ryu@bizplus.co.jp
        simpleEmail("s-ryu@bizplus.co.jp", "テストメール送信", message);

    }
    /**
     * メール送信（文本内容)
     * @param toEmail　宛先
     * @param subject 件名
     * @param msg 送信内容
     */

    public static void simpleEmail(String toEmail, String subject, String msg) {
        SimpleEmail email = new SimpleEmail();
        email.setHostName(HOSTNAME);
        email.setSmtpPort(PORT);
        email.setAuthentication(POP_USERNAME, POP_PASSWORD);
        email.setCharset(CODING);
        try {
            email.addTo(toEmail);
            email.setFrom(POP_USERNAME, USERNAME);
            email.setSubject(subject);
            email.setMsg(msg);
            email.send();
        } catch (EmailException e) {
            log.error(SysConstant.BLANK, e);
        }
    }

	// public static void multiPartEmail(String toEmail, String subject,
	// String msg, String filePath, String fileName) {
	// MultiPartEmail email = new MultiPartEmail();
	// email.setHostName(HOSTNAME);
	// email.setSmtpPort(PORT);
	// email.setAuthentication(POP_USERNAME, POP_PASSWORD);
	// email.setCharset(CODING);
	// try {
	// email.addTo(toEmail);
	// email.setFrom(POP_USERNAME, USERNAME);
	// email.setSubject(subject);
	// email.setMsg(msg);
	// EmailAttachment attachment = new EmailAttachment();
	// attachment.setPath("d:/student_templet.xls");
	// // attachment.setURL(new URL("filePath"));
	// attachment.setDisposition(EmailAttachment.ATTACHMENT);
	// attachment.setDescription("Description");
	// attachment.setName("student_templet.xls");// fileName
	//
	// email.attach(attachment);
	// email.send();
	// } catch (EmailException e) {
	//
	// e.printStackTrace();
	// }
	// }
	//
	//
	// public static void htmlEmail(String toEmail, String subject, String msg)
	// {
	//
	// HtmlEmail email = new HtmlEmail();
	// email.setHostName(HOSTNAME);
	// email.setSmtpPort(PORT);
	// email.setAuthentication(POP_USERNAME, POP_PASSWORD);
	// email.setCharset(CODING);
	// try {
	// email.addTo(toEmail);
	// email.setFrom(POP_USERNAME, USERNAME);
	// email.setSubject(subject);
	// email.setHtmlMsg(msg);
	// // email.setHtmlMsg(msg);
	// email.send();
	// } catch (EmailException e) {
	// e.printStackTrace();
	// }
	// }

}
