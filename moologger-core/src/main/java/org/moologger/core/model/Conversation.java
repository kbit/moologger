package org.moologger.core.model;

import com.google.common.collect.Sets;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.SortedSet;

@Document
public class Conversation implements Comparable<Conversation> {
	
	@Id
	private String id;

	private SortedSet<Message> messages = Sets.newTreeSet();
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public SortedSet<Message> getMessages() {
		return messages;
	}
	
	public void setMessages(SortedSet<Message> messages) {
		this.messages = messages;
	}

    public LocalDateTime getStartTimestamp() {
        if (messages.isEmpty() || messages.first().getTimestamp() == null) {
            return null;
        }

        return messages.first().getTimestamp();
    }

    public LocalDateTime getEndTimestamp() {
        if (messages.isEmpty() || messages.last().getTimestamp() == null) {
            return null;
        }

        return messages.last().getTimestamp();
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

		return new EqualsBuilder().append(messages, conversation.messages)
							      .isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(messages)
									.toHashCode();
	}

    @Override
    public int compareTo(Conversation conversation) {
        if (messages.isEmpty() || conversation.messages.isEmpty()) {
            return 0;
        }

        return messages.first().compareTo(conversation.messages.first());
    }
}