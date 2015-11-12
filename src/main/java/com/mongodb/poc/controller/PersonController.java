/**
 * 
 */
package com.mongodb.poc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import com.mongodb.poc.model.File;
import com.mongodb.poc.model.Person;
import com.mongodb.poc.model.Test;
import com.mongodb.poc.service.FIleUploadImpl;
import com.mongodb.poc.service.PersonService;

/**
 * @author kgurugub
 *
 */

@Controller    
public class PersonController {  
   
	@Autowired
	private PersonService personService;
	
	@Autowired
	private FIleUploadImpl fileUploadImpl;
	
    @RequestMapping(value = "/person", method = RequestMethod.GET)  
	public String getPersonList(ModelMap model) {  
        model.addAttribute("personList", personService.listPerson());  
        return "output";  
    }  
    
    @RequestMapping(value = "/person/save", method = RequestMethod.POST)  
	public View createPerson(@ModelAttribute Person person, ModelMap model) {
    	if(StringUtils.hasText(person.getId())) {
    		personService.updatePerson(person);
    	} else {
    		personService.addPerson(person);
    	}
    	return new RedirectView("/SpringMongoDB/person");  
    }
        
    @RequestMapping(value = "/person/delete", method = RequestMethod.GET)  
	public View deletePerson(@ModelAttribute Person person, ModelMap model) {  
        personService.deletePerson(person);  
        return new RedirectView("/SpringMongoDB/person");  
    }  
    
    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
	public @ResponseBody Test getHomePage(@PathVariable String name) {
    	Test test = new Test();
    	test.setId("101");
    	test.setName(name);
		return test;
 
	}
    
    @RequestMapping(value = "/fileName/{templateName}", method = RequestMethod.GET)
	public @ResponseBody File getTemplateName(@PathVariable String templateName) {
    	File file = fileUploadImpl.getTemplate(templateName);
		return file;
 
	}
}
