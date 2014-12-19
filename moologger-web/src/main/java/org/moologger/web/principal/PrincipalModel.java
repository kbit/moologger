package org.moologger.web.principal;

import org.moologger.core.Principal;

public class PrincipalModel {
	
	private Principal principal;

	public PrincipalModel() {
		this(new Principal());
	}

	public PrincipalModel(Principal principal) {
		this.principal = principal;
	}

	public Principal getPrincipal() {
		return principal;
	}

	public void setPrincipal(Principal principal) {
		this.principal = principal;
	}

}