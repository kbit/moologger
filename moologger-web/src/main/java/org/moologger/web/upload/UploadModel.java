package org.moologger.web.upload;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class UploadModel {
	
	private String client;
	private String protocol;
	private List<MultipartFile> files = new ArrayList<MultipartFile>();
	 
	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public List<MultipartFile> getFiles() {
		return files;
	}
	 
	public void setFiles(List<MultipartFile> files) {
		this.files = files;
	}

}