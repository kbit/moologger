package org.moologger.core.dao.impl;

import javax.persistence.EntityManagerFactory;

import org.moologger.core.Conversation;
import org.moologger.core.Log;
import org.moologger.core.dao.LogDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("logDAO")
public class LogDAOImpl extends DAOImpl<Log> implements LogDAO {

	@Autowired
	public LogDAOImpl(EntityManagerFactory entityManagerFactory) {
		super(Log.class);
		super.setEntityManagerFactory(entityManagerFactory);
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