package org.moologger.web.registry;

import org.moologger.core.parser.Parser;

public interface ParserRegistry {

	public abstract void register(Parser parser);

	public abstract void unregister(Parser parser);

}