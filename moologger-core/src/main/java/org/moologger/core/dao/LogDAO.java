package org.moologger.core.dao;

import org.moologger.core.Conversation;
import org.moologger.core.Log;

public interface LogDAO extends DAO<Log> {
	
	void addConversation(Log log, Conversation conversation);
	
	void deleteConversation(Log log, Conversation conversation);

}