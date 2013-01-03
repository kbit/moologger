package org.moologger.core.dao.impl;

import org.moologger.core.Principal;
import org.springframework.stereotype.Repository;

@Repository
public class PrincipalDAOImpl extends DAOImpl<Principal> {
	
	public PrincipalDAOImpl() {
		super(Principal.class);
	}

}