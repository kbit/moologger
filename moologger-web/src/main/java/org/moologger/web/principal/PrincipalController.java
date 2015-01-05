package org.moologger.web.principal;

import java.util.List;

import org.moologger.core.Principal;
import org.moologger.core.repository.PrincipalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/principals")
public class PrincipalController {

	private PrincipalRepository principalRepository;

	@Autowired
	public PrincipalController(PrincipalRepository principalRepository) {
		this.principalRepository = principalRepository;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String getAllPrincipals(Model model, @ModelAttribute("principals") List<Principal> principals) {
		model.addAttribute(principals);

		return "principals";
	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public ModelAndView getNewPrincipal() {
		return new ModelAndView("principalsNew", "principalModel", new PrincipalModel());
	}

	@RequestMapping(method = RequestMethod.POST)
	public String addNewPrincipal(@ModelAttribute PrincipalModel principalModel) {
		String identifier = principalModel.getPrincipal().getIdentifier();

		if (!principalRepository.existsByIdentifier(identifier)) {
			Principal principal = new Principal();
			principal.setIdentifier(identifier);

			principalRepository.save(principal);
		}

		return "redirect:/principals";
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView getEditPrincipal(@PathVariable String id) {
		Principal principal = principalRepository.findOne(id);

		if (principal == null) {
			return new ModelAndView("principalsEdit");
		}

		return new ModelAndView("principalsEdit", "principalModel", new PrincipalModel(principal));
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public String updateEditPrincipal(@ModelAttribute PrincipalModel principalModel, @PathVariable String id) {
		principalRepository.save(principalModel.getPrincipal());

		return "redirect:/principals";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String deleteEditPrincipal(@PathVariable String id) {
		principalRepository.delete(id);

		return "redirect:/principals";
	}

	@ModelAttribute("principals")
	public List<Principal> getPrincipals() {
		return principalRepository.findAll();
	}

}