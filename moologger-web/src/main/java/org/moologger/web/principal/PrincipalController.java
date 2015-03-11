package org.moologger.web.principal;

import java.util.List;
import java.util.Objects;

import org.moologger.core.model.Alias;
import org.moologger.core.model.Principal;
import org.moologger.core.repository.ConversationRepository;
import org.moologger.core.repository.PrincipalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/principals")
public class PrincipalController {

	private PrincipalRepository principalRepository;

    private ConversationRepository conversationRepository;

	@Autowired
	public PrincipalController(PrincipalRepository principalRepository, ConversationRepository conversationRepository) {
		this.principalRepository = principalRepository;
        this.conversationRepository = conversationRepository;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String getAllPrincipals(Model model, @ModelAttribute("principals") List<Principal> principals, @ModelAttribute("aliases") List<Alias> aliases) {
		model.addAttribute(principals);
        model.addAttribute(aliases);

		return "principals";
	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public ModelAndView getNewPrincipal(Model model, @ModelAttribute("aliases") List<Alias> aliases) {
        model.addAttribute(aliases);

        return new ModelAndView("principalsNew", "principalModel", new PrincipalModel());
	}

	@RequestMapping(method = RequestMethod.POST)
	public String addNewPrincipal(@ModelAttribute PrincipalModel principalModel) {
        Principal principal = principalModel.getPrincipal();
		String identifier = principal.getIdentifier();

		if (!principalRepository.existsByIdentifier(identifier)) {
			principalRepository.save(principal);
		}

		return "redirect:/principals";
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView getEditPrincipal(Model model, @ModelAttribute("aliases") List<Alias> aliases, @PathVariable String id) {
        model.addAttribute(aliases);

		Principal principal = principalRepository.findOne(id);

		if (principal == null) {
			return new ModelAndView("principalsEdit");
		}

		return new ModelAndView("principalsEdit", "principalModel", new PrincipalModel(principal));
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public String updateEditPrincipal(@ModelAttribute PrincipalModel principalModel, @PathVariable String id) {
        Principal principal = principalModel.getPrincipal();
        principal.setId(id);

		principalRepository.save(principal);

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

    @ModelAttribute("aliases")
    public List<Alias> getAliases() {
        return conversationRepository.generateAliases();
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(List.class, "principal.aliases", new CustomCollectionEditor(List.class) {
            @Override
            protected Object convertElement(Object element) {
                return principalRepository.findOneAliasByAliasKey(Objects.toString(element));
            }
        });
    }

}