package org.moologger.core.parser.impl;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.lang3.StringUtils;
import org.cyberneko.html.parsers.DOMParser;
import org.dom4j.Document;
import org.dom4j.Node;
import org.dom4j.io.DOMReader;
import org.dom4j.io.DocumentResult;
import org.dom4j.io.DocumentSource;
import org.moologger.core.model.Conversation;
import org.moologger.core.model.Message;
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

		List<Node> messages = node.selectNodes("message");
		
		for (Node message : messages) {
			conversation.getMessages().add(getMessage(message, getConversationTimestamp(node)));
		}
		
		return conversation;
	}

	private LocalDateTime getConversationTimestamp(Node node) throws ParserException {
        LocalDateTime conversationTimestamp = null;

		Node conversationTimestampNode = node.selectSingleNode("timestamp");

		if (conversationTimestampNode != null) {
            conversationTimestamp = getConversationTimestamp(conversationTimestampNode.getText());
        }

        return conversationTimestamp;
	}

	protected abstract LocalDateTime getConversationTimestamp(String conversationStartTimestampString) throws ParserException;
	
	private Message getMessage(Node node, LocalDateTime conversationTimestamp) throws ParserException {
		Message message = new Message();

		message.setClient(getClient());
		message.setProtocol(getProtocol());
		message.setTimestamp(getMessageTimestamp(node, conversationTimestamp));
		message.setAlias(getAlias(node));
		message.setText(getText(node));
		
		return message;
	}

	private LocalDateTime getMessageTimestamp(Node node, LocalDateTime conversationTimestamp) throws ParserException {
        LocalDateTime timestamp = null;

		Node timestampNode = node.selectSingleNode("timestamp");

		if (timestampNode != null) {
			timestamp = getMessageTimestamp(timestampNode.getText(), conversationTimestamp);
		}

		return timestamp;
	}

	protected abstract LocalDateTime getMessageTimestamp(String timestampString, LocalDateTime conversationTimestamp) throws ParserException;
	
	private String getAlias(Node node) throws ParserException {
		String alias = StringUtils.EMPTY;

		Node aliasNode = node.selectSingleNode("alias");
		
		if (aliasNode != null) {
            alias = aliasNode.getText();
		}
		
		return alias;
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