package org.moologger.web.conversation;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.moologger.core.Conversation;
import org.moologger.core.parser.registry.ParserRegistry;
import org.moologger.core.repository.ConversationRepository;
import org.moologger.core.parser.Parser;
import org.moologger.core.parser.ParserException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/conversations")
public class ConversationController {
	
	@Resource
	private ParserRegistry parserRegistry;
	
	@Resource
	private ConversationRepository conversationRepository;

	@RequestMapping(method = RequestMethod.GET)
	public String get(Model model, @ModelAttribute("conversations") List<Conversation> conversations) {
		model.addAttribute(conversations);
		
		return "conversations";
	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public ModelAndView getNew() {
		return new ModelAndView("conversationsNew", "command", new ConversationModel());
	}
	
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public String addNewConversation(@ModelAttribute ConversationModel logModel) {
		String client = logModel.getClient();
		String protocol = logModel.getProtocol();
		List<MultipartFile> files = logModel.getFiles();
		
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

	@ModelAttribute("conversations")
	public List<Conversation> getConversations() {
		return conversationRepository.findAll();
	}
	  
}