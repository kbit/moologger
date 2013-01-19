package org.moologger.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

@Entity
@Table(name = "logs_t")
public class Log {
	
	@Id
	@Column(name = "log_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "log_id_s")
	@SequenceGenerator(name = "log_id_s", sequenceName = "log_id_s", allocationSize = 1)
	private Long logId;
	
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	@JoinTable(name = "logs_principals",
			   joinColumns = 		@JoinColumn(name = "log_id"),
			   inverseJoinColumns = @JoinColumn(name = "principal_id")
	)
	private Set<Principal> principals = new HashSet<Principal>();
	
	@OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "logs_conversations_t",
    		   joinColumns = 		@JoinColumn(name = "log_id"),
    		   inverseJoinColumns = @JoinColumn(name = "conversation_id")
    )
	private List<Conversation> conversations = new ArrayList<Conversation>();

	public Long getLogId() {
		return logId;
	}

	public void setLogId(Long logId) {
		this.logId = logId;
	}
	
	public Set<Principal> getPrincipals() {
		return Collections.unmodifiableSet(principals);
	}
	
	public void addPrincipal(Principal principal) {
		principals.add(principal);
	}
	
	public void deletePrincipal(Principal principal) {
		principals.remove(principal);
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