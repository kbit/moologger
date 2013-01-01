package org.moologger.core.parser;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.lang.StringUtils;
import org.cyberneko.html.parsers.DOMParser;
import org.dom4j.Document;
import org.dom4j.Node;
import org.dom4j.io.DOMReader;
import org.dom4j.io.DocumentResult;
import org.dom4j.io.DocumentSource;
import org.moologger.core.Conversation;
import org.moologger.core.Log;
import org.moologger.core.Message;
import org.moologger.core.Principal;
import org.moologger.core.Protocol;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public abstract class XMLParser implements Parser {
	
	public Log parse(InputStream inputStream) throws ParserException {
		Log log = new Log();

		try {
			DOMParser parser = new DOMParser();
			parser.parse(new InputSource(inputStream));
			
			DOMReader reader = new DOMReader();
			Document document = reader.read(parser.getDocument());

			Transformer transformer = TransformerFactory.newInstance().newTransformer(getXSLT());
	        DocumentSource source = new DocumentSource(document);
	        DocumentResult result = new DocumentResult();
	        transformer.transform(source, result);

	        log = getLog(result.getDocument());
		} catch (IOException ioe) {
			throw new ParserException(ioe);
		} catch (SAXException saxe) {
			throw new ParserException(saxe);
		} catch (TransformerConfigurationException tce) {
			throw new ParserException(tce);
		} catch (TransformerException te) {
			throw new ParserException(te);
		}
		
        return log;
	}
	
	protected abstract StreamSource getXSLT() throws ParserException;
	
	private Log getLog(Document document) throws ParserException {
		Node log = document.selectSingleNode("log");
		
		return getLog(log);
	}
	
	@SuppressWarnings("unchecked")
	private Log getLog(Node node) throws ParserException {
		Log log = new Log();
		
		log.setStartTimestamp(getLogStartTimestamp(node));
		log.setEndTimestamp(getLogEndTimestamp(node));
		
		List<Node> conversations = node.selectNodes("conversation");
		
		for (Node conversation :conversations) {
			log.addConversation(getConversation(conversation));
		}
		
		return getLog(log);
	}
	
	private Date getLogStartTimestamp(Node node) throws ParserException {
		Date logStartTimestamp = null;
		
		Node logStartTimestampNode = node.selectSingleNode("startTimestamp");
		
		if (logStartTimestampNode != null) {
			logStartTimestamp = getLogStartTimestamp(logStartTimestampNode.getText());
		}
		
		return logStartTimestamp;
	}
	
	protected abstract Date getLogStartTimestamp(String logStartTimestampString) throws ParserException;
	
	private Date getLogEndTimestamp(Node node) throws ParserException {
		Date logEndTimestamp = null;
		
		Node logEndTimestampNode = node.selectSingleNode("endTimestamp");
		
		if (logEndTimestampNode != null) {
			logEndTimestamp = getConversationEndTimestamp(logEndTimestampNode.getText());
		}
		
		return logEndTimestamp;
	}
	
	protected abstract Date getLogEndTimestamp(String logEndTimestampString) throws ParserException;
	
	protected abstract Log getLog(Log log);
	
	@SuppressWarnings("unchecked")
	private Conversation getConversation(Node node) throws ParserException {
		Conversation conversation = new Conversation();

		conversation.setStartTimestamp(getConversationStartTimestamp(node));
		conversation.setEndTimestamp(getConversationEndTimestamp(node));
		
		List<Node> messages = node.selectNodes("message");
		
		for (Node message : messages) {
			conversation.addMessage(getMessage(message, conversation.getStartTimestamp(), conversation.getEndTimestamp()));
		}
		
		return getConversation(conversation);
	}
	
	private Date getConversationStartTimestamp(Node node) throws ParserException {
		Date conversationStartTimestamp = null;
		
		Node conversationStartTimestampNode = node.selectSingleNode("startTimestamp");
		
		if (conversationStartTimestampNode != null) {
			conversationStartTimestamp = getConversationStartTimestamp(conversationStartTimestampNode.getText());
		}
		
		return conversationStartTimestamp;
	}
	
	protected abstract Date getConversationStartTimestamp(String conversationStartTimestampString) throws ParserException;
	
	private Date getConversationEndTimestamp(Node node) throws ParserException {
		Date conversationEndTimestamp = null;
		
		Node conversationEndTimestampNode = node.selectSingleNode("endTimestamp");
		
		if (conversationEndTimestampNode != null) {
			conversationEndTimestamp = getConversationEndTimestamp(conversationEndTimestampNode.getText());
		}
		
		return conversationEndTimestamp;
	}
	
	protected abstract Date getConversationEndTimestamp(String endTimestampString) throws ParserException;
	
	protected abstract Conversation getConversation(Conversation conversation);
	
	private Message getMessage(Node node, Date startTimestamp, Date endTimestamp) throws ParserException {
		Message message = new Message();
		
		message.setTimestamp(getTimestamp(node, startTimestamp, endTimestamp));
		message.setPrincipal(getPrincipal(node));
		message.setText(getText(node));
		
		return message;
	}
	
	private Date getTimestamp(Node node, Date startTimestamp, Date endTimestamp) throws ParserException {
		Date timestamp = null;
		
		Node timestampNode = node.selectSingleNode("timestamp");

		if (timestampNode != null) {
			timestamp = getTimestamp(timestampNode.getText(), startTimestamp, endTimestamp);
		}
		
		return timestamp;
	}
	
	protected abstract Date getTimestamp(String timestampString, Date startTimestamp, Date endTimestamp) throws ParserException;
	
	private Principal getPrincipal(Node node) throws ParserException {
		Principal principal = new Principal();
		
		Node principalNode = node.selectSingleNode("principal");
		
		principal.setProtocol(getProtocol());
		
		if (principalNode != null) {
			principal.setIdentifier(getIdentifier(principalNode.getText()));
		}
		
		return principal;
	}
	
	protected abstract Protocol getProtocol() throws ParserException;
	
	protected abstract String getIdentifier(String identifierString) throws ParserException;
	
	@SuppressWarnings("unchecked")
	private String getText(Node node) throws ParserException {
		String text = StringUtils.EMPTY;
		
		Node textNode = node.selectSingleNode("text");
		
		if (textNode != null) {
			text = getText(textNode.selectNodes("child::node()"));
		}
		
		return text;
	}
	
	protected abstract String getText(List<Node> nodes) throws ParserException;

}