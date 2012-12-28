package org.moologger.core.dao.impl;

import javax.persistence.EntityManagerFactory;

import org.moologger.core.Conversation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ConversationDAOImpl extends DAOImpl<Conversation> {
	
	@Autowired
	public ConversationDAOImpl(EntityManagerFactory entityManagerFactory) {
		super(Conversation.class);
		super.setEntityManagerFactory(entityManagerFactory);
	}

}