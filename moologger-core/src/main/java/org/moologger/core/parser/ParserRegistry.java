package org.moologger.core.parser;

public interface ParserRegistry {
	
	Parser getParser(String client, String protocol);

}