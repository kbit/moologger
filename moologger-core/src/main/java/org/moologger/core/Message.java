package org.moologger.core;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.util.Date;

public class Message {

	private Date timestamp;

	private String identifier;

	private String text;

	public Date getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
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
								  .append(identifier, message.identifier)
								  .append(text, message.text)
								  .isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(timestamp)
									.append(identifier)
									.append(text)
									.toHashCode();
	}

}