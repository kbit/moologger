package org.moologger.core.dao.impl;

import org.moologger.core.Alias;
import org.springframework.stereotype.Repository;

@Repository
public class AliasDAOImpl extends DAOImpl<Alias> {
	
	public AliasDAOImpl() {
		super(Alias.class);
	}

}