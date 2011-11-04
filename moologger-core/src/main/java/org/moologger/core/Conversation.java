package org.moologger.core;

import java.util.Date;
import java.util.List;

public class Conversation {
	
	private List<Message> messages;
	private Date conversationStart;
	private Date conversationEnd;
	
	public List<Message> getMessages() {
		return messages;
	}
	
	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
	
	public Date getConversationStart() {
		return conversationStart;
	}
	
	public void setConversationStart(Date conversationStart) {
		this.conversationStart = conversationStart;
	}
	
	public Date getConversationEnd() {
		return conversationEnd;
	}
	
	public void setConversationEnd(Date conversationEnd) {
		this.conversationEnd = conversationEnd;
	}
	
}