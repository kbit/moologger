package org.moologger.core;

import java.util.ArrayList;
import java.util.List;

public enum Protocol {
	
	OSCAR("Oscar");
	
	private String description;
	
	private static final List<String> descriptions = new ArrayList<String>();
	
	static {
		for (Protocol protocol : Protocol.values()) {
			descriptions.add(protocol.description());
		}
	}
	
	public static List<String> descriptions() {
		return descriptions;
	}
	
	private Protocol(String description) {
		this.description = description;
	}

	public String description() {
		return description;
	}
	
}