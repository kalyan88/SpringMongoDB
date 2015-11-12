/**
 * 
 */
package com.mongodb.poc.model;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author kgurugub
 *
 */
public class FileUpload {
	
	MultipartFile file;

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
}
