package org.moologger.core;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
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

	private List<Message> messages = new ArrayList<>();
	
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
		return messages;
	}
	
	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null) {
			return false;
		}

		if (getClass() != obj.getClass()) {
			return false;
		}

		Conversation conversation = (Conversation) obj;

		return new EqualsBuilder().append(client, conversation.client)
				                  .append(protocol, conversation.protocol)
								  .append(startTimestamp, conversation.startTimestamp)
								  .append(endTimestamp, conversation.endTimestamp)
								  .append(messages, conversation.messages)
							      .isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(client)
									.append(protocol)
									.append(startTimestamp)
									.append(endTimestamp)
									.append(messages)
									.toHashCode();
	}
	
}