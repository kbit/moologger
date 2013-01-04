package org.moologger.core.dao;

import java.util.List;

import org.moologger.core.Conversation;
import org.moologger.core.Log;
import org.moologger.core.Message;
import org.moologger.core.Alias;

public interface MoologgerService {
	
	Log addLog(Log log);
	
	List<Log> getAllLogs();
	
	Log getLog(long logId);
	
	Log saveLog(Log log);
	
	void deleteLog(Log log);
	
	Log addConversation(Log log, Conversation conversation);
	
	Log deleteConversation(Log log, Conversation conversation);
	
	List<Conversation> getAllConversations();
	
	Conversation getConversation(long conversationId);
	
	Conversation saveConversation(Conversation conversation);
	
	Conversation addMessage(Conversation conversation, Message message);
	
	Conversation deleteMessage(Conversation conversation, Message message);

	Alias addAlias(Alias alias);
	
	boolean aliasExists(String identifier, String client, String protocol);
	
	List<Alias> getAllAliases();
	
	Alias getAlias(long aliasId);
	
	Alias getAlias(String identifier, String client, String protocol);
	
	void deleteAlias(Alias alias);
	
}