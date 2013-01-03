package org.moologger.core;

import java.util.ArrayList;
import java.util.List;

public enum Client {
	
	PIDGIN("Pidgin");
	
	private String description;
	
	private static final List<String> descriptions = new ArrayList<String>();
	
	static {
		for (Client client : Client.values()) {
			descriptions.add(client.description());
		}
	}
	
	public static List<String> descriptions() {
		return descriptions;
	}
	
	private Client(String description) {
		this.description = description;
	}

	public String description() {
		return description;
	}

}