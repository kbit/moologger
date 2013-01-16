package org.moologger.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "logs_t")
public class Log {
	
	@Id
	@Column(name = "log_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "log_id_s")
	@SequenceGenerator(name = "log_id_s", sequenceName = "log_id_s", allocationSize = 1)
	private Long logId;
	
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
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "logs_conversations_t",
    		   joinColumns = 		@JoinColumn(name="log_id"),
    		   inverseJoinColumns = @JoinColumn(name="conversation_id")
    )
	private List<Conversation> conversations = new ArrayList<Conversation>();

	public Long getLogId() {
		return logId;
	}

	public void setLogId(Long logId) {
		this.logId = logId;
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