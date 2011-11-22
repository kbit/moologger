package org.moologger.web.registry.impl;

import java.util.ArrayList;
import java.util.List;

import org.moologger.core.parser.Parser;
import org.moologger.web.registry.ParserRegistry;
import org.springframework.stereotype.Service;

@Service
public class ParserRegistryImpl implements ParserRegistry {
	
	private static List<Parser> parsers = new ArrayList<Parser>();

	public void register(Parser parser) {
		parsers.add(parser);
	}

	public void unregister(Parser parser) {
		parsers.remove(parser);
	}
	
}