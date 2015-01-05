package org.moologger.core;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
public class Principal {
	
	@Id
	private String id;

	private String identifier;

	private List<Alias> aliases = new ArrayList<Alias>();
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public List<Alias> getAliases() {
		return aliases;
	}
	
	public void setAliases(List<Alias> aliases) {
		this.aliases = aliases;
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

		Principal principal = (Principal) obj;

		return new EqualsBuilder().append(identifier, principal.identifier)
								  .append(aliases, principal.aliases)
								  .isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(identifier)
									.append(aliases)
									.toHashCode();
	}

}