package org.moologger.core;

import java.util.Date;

public class Message {
	
	private Date timestamp;
	private Principal principal;
	private String text;
	
	public Date getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
	public Principal getPrincipal() {
		return principal;
	}
	
	public void setPrincipal(Principal principal) {
		this.principal = principal;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}

}