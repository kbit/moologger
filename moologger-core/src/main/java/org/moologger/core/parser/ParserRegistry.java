package org.moologger.core.parser;

import java.util.Set;

public interface ParserRegistry {
	
	Set<String> getClients();
	
	Set<String> getProtocols();
	
	Parser getParser(String client, String protocol);

}