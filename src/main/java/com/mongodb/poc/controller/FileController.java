/**
 * 
 */
package com.mongodb.poc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.mongodb.poc.model.File;
import com.mongodb.poc.model.FileUpload;
import com.mongodb.poc.model.Test;
import com.mongodb.poc.service.FIleUploadImpl;
import com.mongodb.poc.validator.FileValidator;

/**
 * @author kgurugub
 *
 */
@Controller
@RequestMapping("/file.htm")
public class FileController {

	@Autowired
	FileValidator validator;
	
	@Autowired
	private FIleUploadImpl fileUploadImpl;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}

	@RequestMapping(method = RequestMethod.GET)
	public String getForm(Model model) {
		FileUpload fileModel = new FileUpload();
		model.addAttribute("file", fileModel);
		return "file";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String fileUploaded(Model model, @Validated FileUpload file,
			BindingResult result) {

		String returnVal = "success";
		if (result.hasErrors()) {
			returnVal = "file";
		} else {			
			MultipartFile multipartFile = file.getFile();
			File fileString = new File();
			fileString.setFileName(multipartFile.getOriginalFilename());
			fileString.setFile(multipartFile.toString());
			fileUploadImpl.saveFile(fileString);
		}
		return returnVal;
	}
	
}