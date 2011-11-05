package org.moologger.core;

import java.util.Collections;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "logs")
public class Log {
	
	@Id
	@Column(name = "log_id")
	private Long logId;
	
	@OneToMany
    @JoinTable(name = "logs_conversations",
    		   joinColumns = 		@JoinColumn(name="log_id"),
    		   inverseJoinColumns = @JoinColumn(name="conversation_id")
    )
	private List<Conversation> conversations;

	public Long getLogId() {
		return logId;
	}

	public void setLogId(Long logId) {
		this.logId = logId;
	}

	public List<Conversation> getConversations() {
		return Collections.unmodifiableList(conversations);
	}

	public void addConversation(Conversation conversation) {
		conversations.add(conversation);
	}
	
	public void deleteConversation(Conversation conversation) {
		conversations.remove(conversation);
	}

}