package org.moologger.core.parser.registry.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.moologger.core.parser.Parser;
import org.moologger.core.parser.ParserRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParserRegistryImpl implements ParserRegistry {
	
	@Autowired
	private List<Parser> parsers;
	
	public Parser getParser(String client, String protocol) {
		Parser match = null;
		
		for (Parser parser : getParsers()) {
			if (StringUtils.equals(parser.getClient(), client) && StringUtils.equals(parser.getProtocol(), protocol)) {
				match = parser;
				break;
			}
		}
		
		return match;
	}

	public List<Parser> getParsers() {
		return parsers;
	}

	public void setParsers(List<Parser> parsers) {
		this.parsers = parsers;
	}

}