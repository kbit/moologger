package org.moologger.core.model;

public enum Client {
	
	PIDGIN("Pidgin");
	
	private String description;
	
	private Client(String description) {
		this.description = description;
	}

	public String description() {
		return description;
	}

}