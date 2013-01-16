package org.moologger.core;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

@Entity
@Table(name = "aliases_t")
public class Alias {
	
	@Id
	@Column(name = "alias_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "alias_id_s")
	@SequenceGenerator(name = "alias_id_s", sequenceName = "alias_id_s", allocationSize = 1)
	private Long aliasId;

	@Column(name = "identifier")
	private String identifier;
	
	@Column(name = "client")
	private String client;
	
	@Column(name = "protocol")
	private String protocol;

	public Long getAliasId() {
		return aliasId;
	}

	public void setAliasId(Long aliasId) {
		this.aliasId = aliasId;
	}

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
		return new HashCodeBuilder(17, 37)
			.append(identifier)
	    	.append(client)
	    	.append(protocol)
	    	.toHashCode();
	}

}