package org.moologger.core;

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

}