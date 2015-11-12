/**
 * 
 */
package com.mongodb.poc.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author kgurugub
 *
 */

@Document(collection="templates")
public class File {
	
	
	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	
	private String fileName;
	
	private String file;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

}
