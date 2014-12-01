package org.moologger.core;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Document
public class Log {
	
	@Id
	private String id;

	@DBRef
	private Set<Principal> principals = new HashSet<Principal>();

	@DBRef
	private List<Conversation> conversations = new ArrayList<Conversation>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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