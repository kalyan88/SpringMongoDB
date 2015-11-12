/**
 * 
 */
package com.mongodb.poc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mongodb.poc.mailer.Mail;
import com.mongodb.poc.mailer.Mailer;
import com.mongodb.poc.model.File;

/**
 * @author kgurugub
 *
 */
@Repository
public class FIleUploadImpl {
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	
	public static final String COLLECTION_NAME = "templates";
	
	/**Adding  Templates*/
	public void saveFile(File file) {
		if (!mongoTemplate.collectionExists(COLLECTION_NAME)) {
			mongoTemplate.createCollection(COLLECTION_NAME);
		}
		//file.setId(UUID.randomUUID().toString());
		mongoTemplate.save(file, COLLECTION_NAME);
	}
	
	/**Getting template by Name*/
	public File getTemplate(String templateName){
		File file = new File();
		//mongoTemplate.findOne(new Query(Criteria.where("name").is("KALYAN")), Person.class)
		 file = mongoTemplate.findOne(new Query(Criteria.where("fileName").is(templateName)), File.class);
		 
		 //Sending Email
		 Mail mail = new Mail();
		 mail.setMailFrom("kalyanisno1@gmail.com");
		 mail.setMailTo("mailinator123@gmail.com");
		 mail.setMailSubject("Subject - Send Email using Spring Velocity Template");
		 mail.setTemplateName("emailtemplate.vm");
		 ApplicationContext context = new ClassPathXmlApplicationContext("spring-beans.xml");
		 Mailer mailer = (Mailer) context.getBean("mailer");
		 
		 mailer.sendMail(mail, file.getFile());
		return file;
	}

}
