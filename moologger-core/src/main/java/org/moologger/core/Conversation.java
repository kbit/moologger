package org.moologger.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "conversations_t")
public class Conversation {
	
	@Id
	@Column(name = "conversation_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "conversation_id_s")
	@SequenceGenerator(name = "conversation_id_s", sequenceName = "conversation_id_s", allocationSize = 1)
	private Long conversationId;
	
	@Column(name = "client")
	private String client;
	
	@Column(name = "protocol")
	private String protocol;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "start_timestamp")
	private Date startTimestamp;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "end_timestamp")
	private Date endTimestamp;
	
	@OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "conversations_messages_t",
    		   joinColumns = 		@JoinColumn(name = "conversation_id"),
    		   inverseJoinColumns = @JoinColumn(name = "message_id")
    )
	private List<Message> messages = new ArrayList<Message>();
	
	public Long getConversationId() {
		return conversationId;
	}

	public void setConversationId(Long conversationId) {
		this.conversationId = conversationId;
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