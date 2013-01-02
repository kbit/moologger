package org.moologger.core.parser;

import java.io.InputStream;

import org.moologger.core.Log;

public interface Parser {
	
	String getClient();
	
	String getProtocol();

	Log parse(InputStream inputStream) throws ParserException;

}