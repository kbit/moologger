package org.moologger.core.parser.registry;

import java.util.Set;

import org.moologger.core.parser.Parser;

public interface ParserRegistry {
	
	Set<String> getClients();
	
	Set<String> getProtocols();
	
	Parser getParser(String client, String protocol);

}