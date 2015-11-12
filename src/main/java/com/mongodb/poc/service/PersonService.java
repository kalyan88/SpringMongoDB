package com.mongodb.poc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mongodb.poc.model.Person;

/**
 * @author kgurugub
 *
 */
@Repository
public class PersonService {
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	public static final String COLLECTION_NAME = "person";
	
	/**Adding  Person*/
	public void addPerson(Person person) {
		if (!mongoTemplate.collectionExists(Person.class)) {
			mongoTemplate.createCollection(Person.class);
		}	
		/**Generating random number*/
		person.setId(UUID.randomUUID().toString());
		mongoTemplate.insert(person, COLLECTION_NAME);
	}
	
	/**Getting the list*/
	public List<Person> listPerson() {
		return mongoTemplate.findAll(Person.class, COLLECTION_NAME);
	}
	
	/**Delete the person*/
	public void deletePerson(Person person) {
		mongoTemplate.remove(person, COLLECTION_NAME);
	}
	
	/**Update the Person*/
	public void updatePerson(Person person) {
		mongoTemplate.insert(person, COLLECTION_NAME);		
	}
	

	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}

	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}
	
	
	

}
