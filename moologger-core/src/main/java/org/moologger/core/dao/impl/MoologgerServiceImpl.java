package org.moologger.core.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.moologger.core.Conversation;
import org.moologger.core.Log;
import org.moologger.core.Message;
import org.moologger.core.Alias;
import org.moologger.core.Principal;
import org.moologger.core.dao.DAO;
import org.moologger.core.dao.MoologgerService;
import org.springframework.stereotype.Service;

@Service
public class MoologgerServiceImpl implements MoologgerService {
	
	@Resource(name = "logDAOImpl")
	private DAO<Log> logDAO;
	
	@Resource(name = "conversationDAOImpl")
	private DAO<Conversation> conversationDAO;
	
	@Resource(name = "aliasDAOImpl")
	private DAO<Alias> aliasDAO;
	
	@Resource(name = "principalDAOImpl")
	private DAO<Principal> principalDAO;
	
	public Log addLog(Log log) {
		return getLogDAO().create(log);
	}
	
	public List<Log> getAllLogs() {
		return getLogDAO().getAll();
	}
	
	public Log getLog(long logId) {
		return getLogDAO().get(logId);
	}

	public Log saveLog(Log log) {
		return getLogDAO().set(log);
	}
	
	public void deleteLog(Log log) {
		getLogDAO().delete(log);
	}
	
	public Log addConversation(Log log, Conversation conversation) {
		log.addConversation(conversation);
		return getLogDAO().set(log);
	}
	
	public Log deleteConversation(Log log, Conversation conversation) {
		log.deleteConversation(conversation);
		return getLogDAO().set(log);
	}
	
	public List<Conversation> getAllConversations() {
		return getConversationDAO().getAll();
	}
	
	public Conversation getConversation(long conversationId) {
		return getConversationDAO().get(conversationId);
	}
	
	public Conversation saveConversation(Conversation conversation) {
		return getConversationDAO().set(conversation);
	}
	
	public Conversation addMessage(Conversation conversation, Message message) {
		conversation.addMessage(message);
		return getConversationDAO().set(conversation);
	}

	public Conversation deleteMessage(Conversation conversation, Message message) {
		conversation.deleteMessage(message);
		return getConversationDAO().set(conversation);
	}
	
	public Alias addAlias(Alias alias) {
		return getAliasDAO().create(alias);
	}
	
	public boolean aliasExists(String identifier, String client, String protocol) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("identifier", identifier);
		params.put("client", client);
		params.put("protocol", protocol);
		
		return getAliasDAO().getCount(params) > 0;
	}
	
	public List<Alias> getAllAliases() {
		return getAliasDAO().getAll();
	}
	
	public Alias getAlias(long aliasId) {
		return getAliasDAO().get(aliasId);
	}
	
	public Alias getAlias(String identifier, String client, String protocol) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("identifier", identifier);
		params.put("client", client);
		params.put("protocol", protocol);
		
		return getAliasDAO().get(params);
	}
	
	public void deleteAlias(Alias alias) {
		getAliasDAO().delete(alias);
	}
	
	public Principal addPrincipal(Principal principal) {
		return getPrincipalDAO().create(principal);
	}

	public boolean principalExists(String identifier) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("identifier", identifier);
		
		return getPrincipalDAO().getCount(params) > 0;
	}

	public List<Principal> getAllPrincipals() {
		return getPrincipalDAO().getAll();
	}

	public Principal getPrincipal(long principalId) {
		return getPrincipalDAO().get(principalId);
	}

	public Principal getPrincipal(String identifier) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("identifier", identifier);
		
		return getPrincipalDAO().get(params);
	}

	public Principal savePrincipal(Principal principal) {
		return getPrincipalDAO().set(principal);
	}

	public Principal saveAliases(Principal principal, List<Alias> aliases) {
		principal.setAliases(aliases);
		return getPrincipalDAO().set(principal);
	}

	public void deletePrincipal(Principal principal) {
		getPrincipalDAO().delete(principal);
	}

	public DAO<Log> getLogDAO() {
		return logDAO;
	}

	public void setLogDAO(DAO<Log> logDAO) {
		this.logDAO = logDAO;
	}

	public DAO<Conversation> getConversationDAO() {
		return conversationDAO;
	}

	public void setConversationDAO(DAO<Conversation> conversationDAO) {
		this.conversationDAO = conversationDAO;
	}

	public DAO<Alias> getAliasDAO() {
		return aliasDAO;
	}

	public void setAliasDAO(DAO<Alias> aliasDAO) {
		this.aliasDAO = aliasDAO;
	}

	public DAO<Principal> getPrincipalDAO() {
		return principalDAO;
	}

	public void setPrincipalDAO(DAO<Principal> principalDAO) {
		this.principalDAO = principalDAO;
	}

}