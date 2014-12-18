package org.moologger.web.principal;

import java.util.List;

import javax.annotation.Resource;

import org.moologger.core.Principal;
import org.moologger.core.repository.PrincipalRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/principals")
public class PrincipalController {
	
	@Resource
	private PrincipalRepository principalRepository;

	@RequestMapping(method = RequestMethod.GET)
	public String get(Model model, @ModelAttribute("principals") List<Principal> principals) {
		model.addAttribute(principals);

		return "principals";
	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public ModelAndView getNew() {
		return new ModelAndView("principalsNew", "command", new PrincipalModel());
	}

	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public String addNewPrincipal(@ModelAttribute PrincipalModel identityModel) {
		String identifier = identityModel.getIdentifier();

		if (!principalRepository.existsByIdentifier(identifier)) {
			Principal principal = new Principal();
			principal.setIdentifier(identifier);

			principalRepository.save(principal);
		}

		return "redirect:/principals";
	}

	@ModelAttribute("principals")
	public List<Principal> getPrincipals() {
		return principalRepository.findAll();
	}

}