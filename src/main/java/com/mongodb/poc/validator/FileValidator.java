/**
 * 
 */
package com.mongodb.poc.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.mongodb.poc.model.FileUpload;

/**
 * @author kgurugub
 *
 */
public class FileValidator implements Validator {

	@Override
	public boolean supports(Class<?> paramClass) {
		return FileUpload.class.equals(paramClass);
	}
	
	@Override
	public void validate(Object obj, Errors errors) {
		FileUpload file = (FileUpload) obj;
		  if (file.getFile().getSize() == 0) {
		   errors.rejectValue("file", "valid.file");
		  }
	}

}
