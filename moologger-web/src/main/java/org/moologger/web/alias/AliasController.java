package org.moologger.web.alias;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.math.NumberUtils;
import org.moologger.core.Alias;
import org.moologger.core.Principal;
import org.moologger.core.dao.MoologgerService;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes
@RequestMapping("/principals/{principalId}/aliases")
public class AliasController {
	
	@Resource
	private MoologgerService moologgerService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String get(@PathVariable long principalId, Model model) {
		model.addAttribute("aliases", getAliases());
		
		AliasModel aliasModel = new AliasModel();
		aliasModel.setSelectedAliases(getMoologgerService().getPrincipal(principalId).getAliases());
		model.addAttribute("command", aliasModel);
		
		return "aliases";
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String addPrincipalAlias(@PathVariable long principalId, @ModelAttribute AliasModel aliasModel, BindingResult result) {
		Principal principal = getMoologgerService().getPrincipal(principalId);
		List<Alias> selectedAliases = aliasModel.getSelectedAliases();
		
		getMoologgerService().saveAliases(principal, selectedAliases);
		
		return "redirect:/principals";
	}
	
	@InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(List.class, "selectedAliases", new CustomCollectionEditor(List.class) {
            @Override
            protected Object convertElement(Object element) {
                Long id = null;

                if (element instanceof String) {
                	id = NumberUtils.toLong((String) element);
                } else if (element instanceof Long) {
                    id = (Long) element;
                }

                return getMoologgerService().getAlias(id);
            }
        });
    }
	
	private Map<String, List<Alias>> getAliases() {
		Map<String, List<Alias>> aliases = new HashMap<String, List<Alias>>();
		
		for (Alias alias : getMoologgerService().getAllAliases()) {
			String aliasKey = "Client: " + alias.getClient() + ", " + "Protocol: " + alias.getProtocol();
			List<Alias> aliasValue = aliases.get(aliasKey);
			
			if (aliasValue == null) {
				aliasValue = new ArrayList<Alias>();
				aliases.put(aliasKey, aliasValue);
			}
			
			aliasValue.add(alias);
		}
		
		return aliases;
	}
	
	public MoologgerService getMoologgerService() {
		return moologgerService;
	}
	
	public void setMoologgerService(MoologgerService moologgerService) {
		this.moologgerService = moologgerService;
	}

}