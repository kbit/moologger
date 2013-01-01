package org.moologger.oscar.parser;

import java.io.InputStream;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.xml.transform.stream.StreamSource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.dom4j.Node;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.moologger.core.Conversation;
import org.moologger.core.Log;
import org.moologger.core.Message;
import org.moologger.core.Protocol;
import org.moologger.core.parser.ParserException;
import org.moologger.core.parser.XMLParser;

public class OscarParser extends XMLParser {
	
	private static final String FULL_DATE_FORMAT = "EEE dd MMM yyyy hh:mm:ss aa zzz";
	private static final String TWELVE_HOUR_TIME_FORMAT = "(hh:mm:ss a)";
	
	protected StreamSource getXSLT() {
		InputStream xslt = getClass().getClassLoader().getResourceAsStream("log.xslt");
		return new StreamSource(xslt);
	}
	
	protected Date getLogStartTimestamp(String logStartTimestampString) throws ParserException {
		return null;
	}
	
	protected Date getLogEndTimestamp(String logEndTimestampString) throws ParserException {
		return null;
	}
	
	protected Log getLog(Log log) {
		List<Conversation> conversations = log.getConversations();
		Conversation firstConversation = conversations.get(0);
		Conversation lastConversation = conversations.get(conversations.size() - 1);
		
		log.setStartTimestamp(firstConversation.getStartTimestamp());
		log.setEndTimestamp(lastConversation.getEndTimestamp());
		
		return log;
	}
	
	protected Date getConversationStartTimestamp(String conversationStartTimestampString) throws ParserException {
		Date conversationStartTimestamp = null;
		
		try {
			conversationStartTimestamp = DateUtils.parseDate(conversationStartTimestampString, new String[] {FULL_DATE_FORMAT});
		} catch (ParseException pe) {
			throw new ParserException(pe);
		}
		
		return conversationStartTimestamp;
	}
	
	protected Date getConversationEndTimestamp(String conversationEndTimestampString) throws ParserException {
		return null;
	}
	
	protected Conversation getConversation(Conversation conversation) {
		List<Message> messages = conversation.getMessages();
		Message firstMessage = messages.get(0);
		Message lastMessage = messages.get(messages.size() - 1);
		
		conversation.setStartTimestamp(firstMessage.getTimestamp());
		conversation.setEndTimestamp(lastMessage.getTimestamp());
		
		return conversation;
	}
	
	protected Date getTimestamp(String timestampString, Date startTimestamp, Date endTimestamp) throws ParserException {
		Date timestamp = null;

		try {
			LocalDate date = new LocalDate(startTimestamp);
			LocalTime time = new LocalTime(DateUtils.parseDate(timestampString, new String[] {TWELVE_HOUR_TIME_FORMAT}));
			
			timestamp = date.toLocalDateTime(time).toDate();
		} catch (ParseException pe) {
			throw new ParserException(pe);
		}
		
		return timestamp;
	}
	
	protected Protocol getProtocol() {
		return Protocol.OSCAR;
	}
	
	protected String getIdentifier(String identifierString) {
		return identifierString;
	}
	
	protected String getText(List<Node> nodes) {
		StringBuilder builder = new StringBuilder();
		
		for (Node node : nodes) {
			builder.append(node.asXML());
		}
		
		return StringUtils.stripToEmpty(builder.toString());
	}

}