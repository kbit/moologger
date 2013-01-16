package org.moologger.web.principal;

import javax.annotation.Resource;

import org.moologger.core.Principal;
import org.moologger.core.dao.MoologgerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes
@RequestMapping("/principals")
public class PrincipalController {
	
	@Resource
	private MoologgerService moologgerService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String get(Model model) {
		model.addAttribute("principals", getMoologgerService().getAllPrincipals());
		model.addAttribute("command", new PrincipalModel());
		
		return "principals";
	}
	
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public String addPrincipal(@ModelAttribute PrincipalModel identityModel, BindingResult result) {
		String identifier = identityModel.getIdentifier();
		
		if (!getMoologgerService().principalExists(identifier)) {
			Principal principal = new Principal();
			principal.setIdentifier(identifier);
			
			getMoologgerService().addPrincipal(principal);
		}
		
		return "redirect:/principals";
	}
	
	public MoologgerService getMoologgerService() {
		return moologgerService;
	}
	
	public void setMoologgerService(MoologgerService moologgerService) {
		this.moologgerService = moologgerService;
	}

}