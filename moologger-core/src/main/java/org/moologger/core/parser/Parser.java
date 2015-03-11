package org.moologger.core.parser;

import java.io.InputStream;

import org.moologger.core.model.Conversation;

public interface Parser {
	
	String getClient();
	
	String getProtocol();

	Conversation parse(InputStream inputStream) throws ParserException;

}