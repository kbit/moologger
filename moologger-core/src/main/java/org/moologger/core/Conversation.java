package org.moologger.core;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "conversations")
public class Conversation {
	
	@Id
	@Column(name = "conversation_id")
	private Long conversationId;
	
	@OneToMany
    @JoinTable(name = "conversations_messages",
    		   joinColumns = 		@JoinColumn(name="conversation_id"),
    		   inverseJoinColumns = @JoinColumn(name="message_id")
    )
	private List<Message> messages;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "start_timestamp")
	private Date startTimestamp;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "end_timestamp")
	private Date endTimestamp;
	
	public Long getConversationId() {
		return conversationId;
	}

	public void setConversationId(Long conversationId) {
		this.conversationId = conversationId;
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
	
}