package org.moologger.core.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.moologger.core.Conversation;
import org.moologger.core.Log;
import org.moologger.core.Message;
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
	
	public Principal addPrincipal(Principal principal) {
		return getPrincipalDAO().create(principal);
	}
	
	public boolean principalExists(String identifier, String protocol) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("identifier", identifier);
		params.put("protocol", protocol);
		
		return getPrincipalDAO().getCount(params) > 0;
	}
	
	public List<Principal> getAllPrincipals() {
		return getPrincipalDAO().getAll();
	}
	
	public Principal getPrincipal(long principalId) {
		return getPrincipalDAO().get(principalId);
	}
	
	public Principal getPrincipal(String identifier, String protocol) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("identifier", identifier);
		params.put("protocol", protocol);
		
		return getPrincipalDAO().get(params);
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

	public DAO<Principal> getPrincipalDAO() {
		return principalDAO;
	}

	public void setPrincipalDAO(DAO<Principal> principalDAO) {
		this.principalDAO = principalDAO;
	}

}