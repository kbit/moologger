package org.moologger.web.registry;

import java.util.ArrayList;
import java.util.List;

import org.moologger.core.parser.Parser;
import org.moologger.oscar.parser.OscarParser;

public class ParserRegistry {

	private static List<Parser> parsers = new ArrayList<Parser>();

	public static void register(Parser parser) {
		parsers.add(parser);
	}

	public static void unregister(Parser parser) {
		parsers.remove(parser);
	}
	
	public static Parser getParser() {
		return new OscarParser();
	}

}