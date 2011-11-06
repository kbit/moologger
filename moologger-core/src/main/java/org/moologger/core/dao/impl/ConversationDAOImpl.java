package org.moologger.core.dao.impl;

import javax.persistence.EntityManagerFactory;

import org.moologger.core.Conversation;
import org.moologger.core.Message;
import org.moologger.core.dao.ConversationDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("conversationDAO")
public class ConversationDAOImpl extends DAOImpl<Conversation> implements ConversationDAO {
	
	@Autowired
	public ConversationDAOImpl(EntityManagerFactory entityManagerFactory) {
		super(Conversation.class);
		super.setEntityManagerFactory(entityManagerFactory);
	}

	public void addMessage(Conversation conversation, Message message) {
		conversation.addMessage(message);
		set(conversation);
	}

	public void deleteMessage(Conversation conversation, Message message) {
		conversation.deleteMessage(message);
		set(conversation);
	}

}