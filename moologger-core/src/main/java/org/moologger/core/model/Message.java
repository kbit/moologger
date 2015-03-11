package org.moologger.core.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Date;

public class Message {

	private Date timestamp;

	private String alias;

	private String text;

	public Date getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(Date timestamp) {
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

}