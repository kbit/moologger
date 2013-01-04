package org.moologger.core;

public enum Protocol {
	
	OSCAR("Oscar");
	
	private String description;
	
	private Protocol(String description) {
		this.description = description;
	}

	public String description() {
		return description;
	}
	
}