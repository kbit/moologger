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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

@Controller
@SessionAttributes
@RequestMapping("/conversations")
public class ConversationController {
	
	@Resource
	private ParserRegistry parserRegistry;
	
	@Resource
	private ConversationRepository conversationRepository;

	@RequestMapping(method = RequestMethod.GET)
	public String get(Model model) {
		model.addAttribute("conversations", conversationRepository.findAll());
		model.addAttribute("clients", parserRegistry.getClients());
		model.addAttribute("protocols", parserRegistry.getProtocols());
		model.addAttribute("command", new ConversationModel());
		
		return "conversations";
	}
	
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public String upload(@ModelAttribute ConversationModel logModel, BindingResult result) {
		String client = logModel.getClient();
		String protocol = logModel.getProtocol();
		List<MultipartFile> files = logModel.getFiles();
		
		Parser parser = parserRegistry.getParser(client, protocol);
		
		try {
			for (MultipartFile file : files) {
				Conversation newConversation = parser.parse(file.getInputStream());
				
				conversationRepository.save(newConversation);
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (ParserException pe) {
			pe.printStackTrace();
		}
		
		return "redirect:/conversations";
	}
	  
}