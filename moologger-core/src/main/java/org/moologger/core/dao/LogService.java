package org.moologger.core.dao;

import java.util.List;

import org.moologger.core.Conversation;
import org.moologger.core.Log;
import org.moologger.core.Message;

public interface LogService {
	
	List<Log> getAllLogs();
	
	Log addLog(Log log);
	
	void deleteLog(Log log);
	
	List<Conversation> getAllConversations();
	
	Log addConversation(Log log, Conversation conversation);
	
	Log deleteConversation(Log log, Conversation conversation);
	
	Conversation addMessage(Conversation conversation, Message message);
	
	Conversation deleteMessage(Conversation conversation, Message message);

}
