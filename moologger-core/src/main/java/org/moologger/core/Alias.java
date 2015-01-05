package org.moologger.core;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class Alias {

	private String identifier;

	private String client;

	private String protocol;

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null) {
			return false;
		}

		if (getClass() != obj.getClass()) {
			return false;
		}

		Alias alias = (Alias) obj;

		return new EqualsBuilder().append(identifier, alias.identifier)
								  .append(client, alias.client)
								  .append(protocol, alias.protocol)
								  .isEquals();
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
			.append(identifier)
	    	.append(client)
	    	.append(protocol)
	    	.toHashCode();
	}

}