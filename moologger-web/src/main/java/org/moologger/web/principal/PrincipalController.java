package org.moologger.web.principal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.moologger.core.Alias;
import org.moologger.core.Principal;
import org.moologger.core.repository.PrincipalRepository;
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
@RequestMapping("/principals")
public class PrincipalController {
	
	@Resource
	PrincipalRepository principalRepository;
	
	@RequestMapping(method = RequestMethod.GET)
	public String get(Model model) {
		model.addAttribute("principals", principalRepository.findAll());
		
		return "principals";
	}
	
	@RequestMapping(value="/new", method = RequestMethod.GET)
	public String getNewPrincipal(Model model) {
		model.addAttribute("command", new PrincipalModel());
		
		return "principalsNew";
	}
	
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public String addNewPrincipal(@ModelAttribute PrincipalModel identityModel, BindingResult result) {
		String identifier = identityModel.getIdentifier();

		if (!principalRepository.existsByIdentifier(identifier)) {
			Principal principal = new Principal();
			principal.setIdentifier(identifier);

			principalRepository.save(principal);
		}
		
		return "redirect:/principals";
	}
	
	@RequestMapping(value = "/{principalId}/aliases", method = RequestMethod.GET)
	public String getEditPrincipalAlias(@PathVariable long principalId, Model model) {
		model.addAttribute("aliases", getAliases());
		
		AliasModel aliasModel = new AliasModel();
		aliasModel.setSelectedAliases(principalRepository.findOne(principalId).getAliases());
		model.addAttribute("command", aliasModel);
		
		return "aliasesEdit";
	}
	
	@RequestMapping(value = "/{principalId}/aliases/edit", method = RequestMethod.POST)
	public String editPrincipalAlias(@PathVariable long principalId, @ModelAttribute AliasModel aliasModel, BindingResult result) {
		Principal principal = principalRepository.findOne(principalId);
		List<Alias> selectedAliases = aliasModel.getSelectedAliases();

		for (Alias selectedAlias : selectedAliases) {
			principal.addAlias(selectedAlias);
		}
		
		principalRepository.save(principal);

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

				for (Principal principal : principalRepository.findAll()) {
					for (Alias alias : principal.getAliases()) {
						if (ObjectUtils.equals(alias.getIdentifier(), id)) {
							return alias;
						}
					}
				}

                return null;
            }
        });
    }
	
	private Map<String, List<Alias>> getAliases() {
		Map<String, List<Alias>> aliases = new HashMap<String, List<Alias>>();

		for (Principal principal : principalRepository.findAll()) {
			for (Alias alias : principal.getAliases()) {
				String aliasKey = "Client: " + alias.getClient() + ", " + "Protocol: " + alias.getProtocol();
				List<Alias> aliasValue = aliases.get(aliasKey);

				if (aliasValue == null) {
					aliasValue = new ArrayList<Alias>();
					aliases.put(aliasKey, aliasValue);
				}

				aliasValue.add(alias);
			}
		}
		
		return aliases;
	}

}