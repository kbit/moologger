package org.moologger.web.conversation;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.moologger.core.MoologgerCoreUtil;
import org.moologger.core.model.Conversation;
import org.moologger.core.model.Message;
import org.moologger.core.model.Principal;
import org.moologger.core.parser.registry.ParserRegistry;
import org.moologger.core.repository.ConversationRepository;
import org.moologger.core.parser.Parser;
import org.moologger.core.parser.ParserException;
import org.moologger.core.repository.PrincipalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/conversations")
public class ConversationController {

	private ConversationRepository conversationRepository;

    private PrincipalRepository principalRepository;

	private ParserRegistry parserRegistry;

	@Autowired
	public ConversationController(ConversationRepository conversationRepository, PrincipalRepository principalRepository, ParserRegistry parserRegistry) {
		this.conversationRepository = conversationRepository;
        this.principalRepository = principalRepository;
		this.parserRegistry = parserRegistry;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String getAllConversations(Model model, @ModelAttribute("conversations") List<Conversation> conversations) {
		model.addAttribute(conversations);

        Map<String, String> aliases = Maps.newHashMap();

        for (Conversation conversation : conversations) {
            SortedSet<Message> messages = conversation.getMessages();

            for (Message message : messages) {
				String client = message.getClient();
				String protocol = message.getProtocol();
                String alias = message.getAlias();
                String key = MoologgerCoreUtil.buildAliasKey(client, protocol, alias);

                if (!aliases.containsKey(key)) {
                    Principal principal = principalRepository.findOneByAliasKey(key);
                    String identifier = principal != null ? principal.getIdentifier() : alias;

                    aliases.put(key, identifier);
                }
            }
        }

        model.addAttribute("aliases", aliases);
		
		return "conversations";
	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public ModelAndView getNewConversation(@ModelAttribute("clients") Set<String> clients, @ModelAttribute("protocols") Set<String> protocols) {
		ModelAndView modelAndView = new ModelAndView("conversationsNew", "conversationModel", new ConversationModel());
		modelAndView.addObject(clients);
		modelAndView.addObject(protocols);

		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String addNewConversation(@ModelAttribute ConversationModel conversationModel) {
		String client = conversationModel.getClient();
		String protocol = conversationModel.getProtocol();
		List<MultipartFile> files = conversationModel.getFiles();
		
		Parser parser = parserRegistry.getParser(client, protocol);
		
		try {
			for (MultipartFile file : files) {
				Conversation newConversation = parser.parse(file.getInputStream());
				
				conversationRepository.save(newConversation);
			}
		} catch (IOException | ParserException e) {
			e.printStackTrace();
		}
		
		return "redirect:/conversations";
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView getEditConversation(@PathVariable String id) {
		Conversation conversation = conversationRepository.findOne(id);

		if (conversation == null) {
			return new ModelAndView("conversationsEdit");
		}

		return new ModelAndView("conversationsEdit", "conversationModel", new ConversationModel(conversation));
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public String updateEditConversation(@ModelAttribute ConversationModel conversationModel, @PathVariable String id) {
		conversationRepository.save(conversationModel.getConversation());

		return "redirect:/conversations";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String deleteEditConversation(@PathVariable String id) {
		conversationRepository.delete(id);

		return "redirect:/conversations";
	}

	@ModelAttribute("conversations")
	public SortedSet<Conversation> getConversations() {
		return Sets.newTreeSet(conversationRepository.findAll());
	}

	@ModelAttribute("clients")
	public Set<String> getClients() {
		return parserRegistry.getClients();
	}

	@ModelAttribute("protocols")
	public Set<String> getProtocols() {
		return parserRegistry.getProtocols();
	}
	  
}