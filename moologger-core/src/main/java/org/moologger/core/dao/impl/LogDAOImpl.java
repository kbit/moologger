package org.moologger.core.dao.impl;

import org.moologger.core.Conversation;
import org.moologger.core.Log;
import org.moologger.core.dao.LogDAO;
import org.springframework.stereotype.Component;

@Component("logDAO")
public class LogDAOImpl extends DAOImpl<Log> implements LogDAO {

	public LogDAOImpl() {
		super(Log.class);
	}

	public void addConversation(Log log, Conversation conversation) {
		log.addConversation(conversation);
		set(log);
	}

	public void deleteConversation(Log log, Conversation conversation) {
		log.deleteConversation(conversation);
		set(log);
	}

}