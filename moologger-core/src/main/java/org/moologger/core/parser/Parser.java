package org.moologger.core.parser;

import java.io.InputStream;
import java.util.List;

import org.moologger.core.Log;

public interface Parser {
	
	List<Log> parse(InputStream... inputStreams) throws ParserException;
	
	Log parse(InputStream inputStream) throws ParserException;

}