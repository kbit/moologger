package org.moologger.core;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "principals_t")
public class Principal {
	
	@Id
	@Column(name = "principal_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "principal_id_s")
	@SequenceGenerator(name = "principal_id_s", sequenceName = "principal_id_s", allocationSize = 1)
	private Long principalId;

	@Column(name = "identifier")
	private String identifier;
	
	@Column(name = "protocol")
	@Enumerated(EnumType.STRING)
	private Protocol protocol;

	public Long getPrincipalId() {
		return principalId;
	}

	public void setPrincipalId(Long principalId) {
		this.principalId = principalId;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public Protocol getProtocol() {
		return protocol;
	}

	public void setProtocol(Protocol protocol) {
		this.protocol = protocol;
	}

}