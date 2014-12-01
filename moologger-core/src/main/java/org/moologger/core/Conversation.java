package org.moologger.core;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Document
public class Conversation {
	
	@Id
	private String id;

	private String client;

	private String protocol;

	private Date startTimestamp;

	private Date endTimestamp;

	private List<Message> messages = new ArrayList<Message>();
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
	
	public Date getStartTimestamp() {
		return startTimestamp;
	}
	
	public void setStartTimestamp(Date startTimestamp) {
		this.startTimestamp = startTimestamp;
	}
	
	public Date getEndTimestamp() {
		return endTimestamp;
	}
	
	public void setEndTimestamp(Date endTimestamp) {
		this.endTimestamp = endTimestamp;
	}

	public List<Message> getMessages() {
		return Collections.unmodifiableList(messages);
	}
	
	public void addMessage(Message message) {
		messages.add(message);
	}
	
	public void deleteMessage(Message message) {
		messages.remove(message);
	}
	
}