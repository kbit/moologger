package org.moologger.core.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.moologger.core.Conversation;
import org.moologger.core.Log;
import org.moologger.core.Message;
import org.moologger.core.Alias;
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

}