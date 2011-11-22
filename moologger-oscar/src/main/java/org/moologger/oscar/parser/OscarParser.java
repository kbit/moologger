package org.moologger.oscar.parser;

import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.moologger.core.Conversation;
import org.moologger.core.Log;
import org.moologger.core.Message;
import org.moologger.core.Principal;
import org.moologger.core.Protocol;
import org.moologger.core.parser.Parser;
import org.moologger.core.parser.ParserException;

public class OscarParser implements Parser {
	
	private static final String twelveHourDateFormat = "(h:mm a)";

	public Log parse(InputStream... inputStreams) throws ParserException {
		SAXReader reader = new SAXReader();
		
		List<Document> documents = new ArrayList<Document>();
		try {
			for (InputStream inputStream : inputStreams) {
				documents.add(reader.read(inputStream));
			}
		} catch (DocumentException de) {
			throw new ParserException(de);
		}
		
        return getLog(documents);
	}
	
	private Log getLog(List<Document> documents) throws ParserException {
		Log log = new Log();
		
		for (Document document : documents) {
			log.addConversation(getConversation(document));
		}
		
		return log;
	}
	
	private Conversation getConversation(Document document) throws ParserException {
		Conversation conversation = new Conversation();
		
		List<Node> messages = document.selectNodes("/html/body/font");
		
		for (Node message : messages) {
			conversation.addMessage(getMessage(message));
		}
		
		return conversation;
	}
	
	private Message getMessage(Node node) throws ParserException {
		Message message = new Message();
		
		message.setTimestamp(getTimestamp(node));
		message.setPrincipal(getPrincipal(node));
		message.setText(getText(node));
		
		return message;
	}
	
	private Date getTimestamp(Node node) throws ParserException {
		String timestampText = node.selectSingleNode("font").getText();
		
		Date date = null;
		try {
			date = DateUtils.parseDate(timestampText, new String[] {twelveHourDateFormat});
		} catch (ParseException pe) {
			throw new ParserException(pe);
		}
		
		return date;
	}
	
	private Principal getPrincipal(Node node) {
		String principalText = node.selectSingleNode("b").getText();
		
		Principal principal = new Principal();
		principal.setIdentifier(StringUtils.removeEnd(principalText, ":"));
		principal.setProtocol(Protocol.OSCAR);
		
		return principal;
	}
	
	private String getText(Node node) {
		return node.selectSingleNode("following-sibling::text()[1]").getText();
	}

}