package org.moologger.web.alias;

import java.util.ArrayList;
import java.util.List;

import org.moologger.core.Alias;

public class AliasModel {
	
	private List<Alias> selectedAliases = new ArrayList<Alias>();

	public List<Alias> getSelectedAliases() {
		return selectedAliases;
	}

	public void setSelectedAliases(List<Alias> selectedAliases) {
		this.selectedAliases = selectedAliases;
	}

}