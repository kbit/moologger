package org.moologger.core.dao.impl;

import javax.persistence.EntityManagerFactory;

import org.moologger.core.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LogDAOImpl extends DAOImpl<Log> {

	@Autowired
	public LogDAOImpl(EntityManagerFactory entityManagerFactory) {
		super(Log.class);
		super.setEntityManagerFactory(entityManagerFactory);
	}

}