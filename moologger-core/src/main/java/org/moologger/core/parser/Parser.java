package org.moologger.core.parser;

import java.io.InputStream;

import org.moologger.core.Log;

public interface Parser {

	Log parse(InputStream inputStream) throws ParserException;

}