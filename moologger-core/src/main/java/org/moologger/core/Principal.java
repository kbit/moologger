package org.moologger.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
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
	
	@OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(name = "principals_aliases_t",
    		   joinColumns = 		@JoinColumn(name="principal_id"),
    		   inverseJoinColumns = @JoinColumn(name="alias_id")
    )
	private List<Alias> aliases = new ArrayList<Alias>();
	
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

	public List<Alias> getAliases() {
		return Collections.unmodifiableList(aliases);
	}
	
	public void setAliases(List<Alias> aliases) {
		this.aliases = aliases;
	}
	
	public void deleteAlias(Alias alias) {
		aliases.remove(alias);
	}

}