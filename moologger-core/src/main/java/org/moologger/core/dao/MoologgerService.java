package org.moologger.core.dao;

import java.util.List;

import org.moologger.core.Conversation;
import org.moologger.core.Log;
import org.moologger.core.Message;
import org.moologger.core.Principal;

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

	Principal addPrincipal(Principal principal);
	
	boolean principalExists(String identifier, String protocol);
	
	List<Principal> getAllPrincipals();
	
	Principal getPrincipal(long principalId);
	
	Principal getPrincipal(String identifier, String protocol);
	
	void deletePrincipal(Principal principal);
	
}