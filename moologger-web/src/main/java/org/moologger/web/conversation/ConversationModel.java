package org.moologger.web.conversation;

import java.util.ArrayList;
import java.util.List;

import org.moologger.core.Conversation;
import org.springframework.web.multipart.MultipartFile;

public class ConversationModel {

	private Conversation conversation;

	private List<MultipartFile> files = new ArrayList<>();

	public ConversationModel() {
		this(new Conversation());
	}

	public ConversationModel(Conversation conversation) {
		this.conversation = conversation;
	}

	public Conversation getConversation() {
		return conversation;
	}

	public void setConversation(Conversation conversation) {
		this.conversation = conversation;
	}

	public List<MultipartFile> getFiles() {
		return files;
	}
	 
	public void setFiles(List<MultipartFile> files) {
		this.files = files;
	}

}