package org.moologger.web.upload;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class Upload {
	
	private String name;
	private List<MultipartFile> files = new ArrayList<MultipartFile>();
	 
	public String getName() {
		return name;
	}
	 
	public void setName(String name) {
		this.name = name;
	}
	 
	public List<MultipartFile> getFiles() {
		return files;
	}
	 
	public void setFiles(List<MultipartFile> files) {
		this.files = files;
	}

}