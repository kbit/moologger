package org.moologger.core.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.time.LocalDateTime;

public class Message implements Comparable<Message> {

	private String client;

	private String protocol;

	private LocalDateTime timestamp;

	private String alias;

	private String text;

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

	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
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

		Message message = (Message) obj;

		return new EqualsBuilder().append(timestamp, message.timestamp)
								  .append(alias, message.alias)
								  .append(text, message.text)
								  .isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(timestamp)
									.append(alias)
									.append(text)
									.toHashCode();
	}

    @Override
    public int compareTo(Message message) {
        if (timestamp == null || message.timestamp == null) {
            return 0;
        }

        return timestamp.compareTo(message.timestamp);
    }

}