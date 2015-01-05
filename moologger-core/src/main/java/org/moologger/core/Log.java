package org.moologger.core;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
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
		return principals;
	}
	
	public void setPrincipals(Set<Principal> principals) {
		this.principals = principals;
	}

	public List<Conversation> getConversations() {
		return conversations;
	}

	public void setConversations(List<Conversation> conversations) {
		this.conversations = conversations;
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

		Log log = (Log) obj;

		return new EqualsBuilder().append(principals, log.principals)
							      .append(conversations, log.conversations)
								  .isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(principals)
									.append(conversations)
									.toHashCode();
	}

}