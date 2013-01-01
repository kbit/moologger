package org.moologger.core.dao.impl;

import org.moologger.core.Conversation;
import org.springframework.stereotype.Repository;

@Repository
public class ConversationDAOImpl extends DAOImpl<Conversation> {
	
	public ConversationDAOImpl() {
		super(Conversation.class);
	}

}