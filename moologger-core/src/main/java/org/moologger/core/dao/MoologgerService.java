package org.moologger.core.dao;

import java.util.List;

import org.moologger.core.Alias;
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

	Alias addAlias(Alias alias);
	
	boolean aliasExists(String identifier, String client, String protocol);
	
	List<Alias> getAllAliases();
	
	Alias getAlias(long aliasId);
	
	Alias getAlias(String identifier, String client, String protocol);
	
	void deleteAlias(Alias alias);
	
	Principal addPrincipal(Principal principal);
	
	boolean principalExists(String identifier);
	
	List<Principal> getAllPrincipals();
	
	Principal getPrincipal(long principalId);
	
	Principal getPrincipal(String identifier);
	
	Principal savePrincipal(Principal principal);
	
	Principal saveAliases(Principal principal, List<Alias> aliases);
	
	void deletePrincipal(Principal principal);
	
}