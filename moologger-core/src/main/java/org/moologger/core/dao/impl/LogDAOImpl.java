package org.moologger.core.dao.impl;

import org.moologger.core.Log;
import org.springframework.stereotype.Repository;

@Repository
public class LogDAOImpl extends DAOImpl<Log> {

	public LogDAOImpl() {
		super(Log.class);
	}

}