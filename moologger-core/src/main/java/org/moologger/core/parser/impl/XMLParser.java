package org.moologger.core.parser.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import javax.xml.transform.Transformer;
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
import org.moologger.core.Message;
import org.moologger.core.parser.Parser;
import org.moologger.core.parser.ParserException;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public abstract class XMLParser implements Parser {
	
	public Conversation parse(InputStream inputStream) throws ParserException {
		Conversation conversation;

		try {
			DOMParser parser = new DOMParser();
			parser.parse(new InputSource(inputStream));
			
			DOMReader reader = new DOMReader();
			Document document = reader.read(parser.getDocument());

			Transformer transformer = TransformerFactory.newInstance().newTransformer(getXSLT());
	        DocumentSource source = new DocumentSource(document);
	        DocumentResult result = new DocumentResult();
	        transformer.transform(source, result);

	        conversation = getConversation(result.getDocument());
		} catch (IOException | SAXException | TransformerException e) {
			throw new ParserException(e);
		}
		
        return conversation;
	}
	
	protected abstract StreamSource getXSLT() throws ParserException;

	private Conversation getConversation(Document document) throws ParserException {
		Node conversation = document.selectSingleNode("conversation");
		
		return getConversation(conversation);
	}

	@SuppressWarnings("unchecked")
	private Conversation getConversation(Node node) throws ParserException {
		Conversation conversation = new Conversation();

		conversation.setClient(getClient());
		conversation.setProtocol(getProtocol());
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
		message.setIdentifier(getIdentifier(node));
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
	
	private String getIdentifier(Node node) throws ParserException {
		String identifier = StringUtils.EMPTY;

		Node aliasNode = node.selectSingleNode("alias");
		
		if (aliasNode != null) {
			identifier = aliasNode.getText();
		}
		
		return identifier;
	}
	
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