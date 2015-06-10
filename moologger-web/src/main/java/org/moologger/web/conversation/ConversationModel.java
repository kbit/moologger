package org.moologger.web.conversation;

import java.util.ArrayList;
import java.util.List;

import org.moologger.core.model.Conversation;
import org.springframework.web.multipart.MultipartFile;

public class ConversationModel {

	private String client;

	private String protocol;

	private List<MultipartFile> files = new ArrayList<>();

	private Conversation conversation;

	public ConversationModel() {
		this(new Conversation());
	}

	public ConversationModel(Conversation conversation) {
		this.conversation = conversation;
	}

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

	public Conversation getConversation() {
		return conversation;
	}

	public void setConversation(Conversation conversation) {
		this.conversation = conversation;
	}

}