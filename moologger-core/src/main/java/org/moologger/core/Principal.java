package org.moologger.core;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Collections;
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
		return Collections.unmodifiableList(aliases);
	}
	
	public void setAliases(List<Alias> aliases) {
		this.aliases = aliases;
	}

	public void addAlias(Alias alias) {
		aliases.add(alias);
	}
	
	public void deleteAlias(Alias alias) {
		aliases.remove(alias);
	}

}