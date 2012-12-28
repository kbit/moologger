package org.moologger.core.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.moologger.core.Conversation;
import org.moologger.core.Log;
import org.moologger.core.Message;
import org.moologger.core.dao.DAO;
import org.moologger.core.dao.LogService;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl implements LogService {
	
	@Resource(name = "logDAOImpl")
	private DAO<Log> logDAO;
	
	@Resource(name = "conversationDAOImpl")
	private DAO<Conversation> conversationDAO;
	
	public List<Log> getAllLogs() {
		return getLogDAO().getAll();
	}
	
	public Log addLog(Log log) {
		return getLogDAO().create(log);
	}
	
	public void deleteLog(Log log) {
		getLogDAO().delete(log);
	}
	
	public List<Conversation> getAllConversations() {
		return getConversationDAO().getAll();
	}
	
	public Log addConversation(Log log, Conversation conversation) {
		log.addConversation(conversation);
		return getLogDAO().set(log);
	}

	public Log deleteConversation(Log log, Conversation conversation) {
		log.deleteConversation(conversation);
		return getLogDAO().set(log);
	}
	
	public Conversation addMessage(Conversation conversation, Message message) {
		conversation.addMessage(message);
		return getConversationDAO().set(conversation);
	}

	public Conversation deleteMessage(Conversation conversation, Message message) {
		conversation.deleteMessage(message);
		return getConversationDAO().set(conversation);
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

}