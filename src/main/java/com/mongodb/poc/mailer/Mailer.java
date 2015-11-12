/**
 * 
 */
package com.mongodb.poc.mailer;

import java.io.StringWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.multipart.MultipartFile;

import com.mongodb.poc.model.FileUpload;

/**
 * @author kgurugub
 *
 */
public class Mailer {
	
	private MailSender mailSender;
	
	private VelocityEngine velocityEngine;

	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}
	
	public void sendMail(Mail mail, String velocityTemp) {
		  SimpleMailMessage message = new SimpleMailMessage();
		  
		  message.setFrom(mail.getMailFrom());
		  message.setTo(mail.getMailTo());
		  message.setSubject(mail.getMailSubject());
		
		  Template template = velocityEngine.getTemplate("./templates/" + mail.getTemplateName());

		  VelocityContext velocityContext = new VelocityContext();
		  velocityContext.put("firstName", "Hima");
		  velocityContext.put("lastName", "Pam");
		  velocityContext.put("location", "VA");
		  
		  StringWriter stringWriter = new StringWriter();
		  
		  template.merge(velocityContext, stringWriter);
		  
		  message.setText(stringWriter.toString());
		  
		  mailSender.send(message);
		 }

}
