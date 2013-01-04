package org.moologger.core;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

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

}