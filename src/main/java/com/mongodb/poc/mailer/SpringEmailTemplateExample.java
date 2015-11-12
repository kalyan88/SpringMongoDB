/**
 * 
 */
package com.mongodb.poc.mailer;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author kgurugub
 *
 */
public class SpringEmailTemplateExample {
	
	public static void main(String[] args) {
		  Mail mail = new Mail();
		  mail.setMailFrom("kalyanisno1@gmail.com");
		  mail.setMailTo("mailinator123@gmail.com");
		  mail.setMailSubject("Subject - Send Email using Spring Velocity Template");
		  mail.setTemplateName("emailtemplate.vm");
		  ApplicationContext context = new ClassPathXmlApplicationContext("/WEB-INF/dispatcher-servlet.xml");
		  Mailer mailer = (Mailer) context.getBean("mailer");
	//  mailer.sendMail(mail);

		 }

}
