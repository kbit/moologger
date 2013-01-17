package org.moologger.web.log;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.moologger.core.Log;
import org.moologger.core.dao.MoologgerService;
import org.moologger.core.parser.Parser;
import org.moologger.core.parser.ParserException;
import org.moologger.core.parser.registry.impl.ParserRegistryImpl;
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
@RequestMapping("/logs")
public class LogController {
	
	@Resource
	private ParserRegistryImpl parserRegistry;
	
	@Resource
	private MoologgerService moologgerService;

	@RequestMapping(method = RequestMethod.GET)
	public String get(Model model) {
		model.addAttribute("logs", getMoologgerService().getAllLogs());
		model.addAttribute("clients", getParserRegistry().getClients());
		model.addAttribute("protocols", getParserRegistry().getProtocols());
		model.addAttribute("command", new LogModel());
		
		return "logs";
	}
	
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public String upload(@ModelAttribute LogModel logModel, BindingResult result) {
		String client = logModel.getClient();
		String protocol = logModel.getProtocol();
		List<MultipartFile> files = logModel.getFiles();
		
		Parser parser = getParserRegistry().getParser(client, protocol);
		
		try {
			for (MultipartFile file : files) {
				Log newLog = parser.parse(file.getInputStream());
				
				getMoologgerService().saveLog(newLog);
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (ParserException pe) {
			pe.printStackTrace();
		}
		
		return "redirect:/logs";
	}
	
	public ParserRegistryImpl getParserRegistry() {
		return parserRegistry;
	}

	public void setParserRegistry(ParserRegistryImpl parserRegistry) {
		this.parserRegistry = parserRegistry;
	}

	public MoologgerService getMoologgerService() {
		return moologgerService;
	}
	
	public void setMoologgerService(MoologgerService moologgerService) {
		this.moologgerService = moologgerService;
	}
	  
}