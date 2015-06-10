package org.moologger.pidgin.oscar.parser;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.List;

import javax.xml.transform.stream.StreamSource;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Node;
import org.moologger.core.model.Client;
import org.moologger.core.model.Protocol;
import org.moologger.core.parser.ParserException;
import org.moologger.core.parser.impl.XMLParser;
import org.springframework.stereotype.Component;

@Component
public class PidginOscarParser extends XMLParser {

	private static final String FULL_DATE_FORMAT = "EEE dd MMM yyyy hh:mm:ss aa zzz";
	private static final String DATE_FORMAT = "MM/dd/yyyy";
	private static final String TIME_FORMAT = "hh:mm:ss a";
	
	public String getClient() {
		return Client.PIDGIN.description();
	}
	
	public String getProtocol() {
		return Protocol.OSCAR.description();
	}
	
	protected StreamSource getXSLT() {
		InputStream xslt = getClass().getClassLoader().getResourceAsStream("org/moologger/pidgin/oscar/pidgin-oscar.xslt");
		return new StreamSource(xslt);
	}
	
	protected LocalDateTime getConversationTimestamp(String conversationTimestampString) throws ParserException {
		return LocalDateTime.parse(conversationTimestampString, DateTimeFormatter.ofPattern(FULL_DATE_FORMAT));
	}
	
	protected LocalDateTime getMessageTimestamp(String timestampString, LocalDateTime conversationTimestamp) throws ParserException {
		DateTimeFormatterBuilder builder = new DateTimeFormatterBuilder()
                .appendLiteral("(")
                .optionalStart().appendPattern(DATE_FORMAT).appendLiteral(" ").optionalEnd()
                .appendPattern(TIME_FORMAT)
                .appendLiteral(")");

        builder.parseDefaulting(ChronoField.YEAR, ChronoField.YEAR.getFrom(conversationTimestamp));
        builder.parseDefaulting(ChronoField.MONTH_OF_YEAR, ChronoField.MONTH_OF_YEAR.getFrom(conversationTimestamp));
        builder.parseDefaulting(ChronoField.DAY_OF_MONTH, ChronoField.DAY_OF_MONTH.getFrom(conversationTimestamp));

        return LocalDateTime.parse(timestampString, builder.toFormatter());
	}
	
	protected String getText(List<Node> nodes) {
		StringBuilder builder = new StringBuilder();
		
		for (Node node : nodes) {
			builder.append(node.asXML());
		}
		
		return StringUtils.stripToEmpty(builder.toString());
	}

}