package org.moologger.web.upload;

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
@RequestMapping("/upload")
public class UploadController {
	
	@Resource
	private ParserRegistryImpl parserRegistry;
	
	@Resource
	private MoologgerService moologgerService;

	@RequestMapping(method = RequestMethod.GET)
	public void get(Model model) {
		model.addAttribute("clients", getParserRegistry().getClients());
		model.addAttribute("protocols", getParserRegistry().getProtocols());
		model.addAttribute("command", new UploadModel());
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String upload(@ModelAttribute UploadModel uploadModel, BindingResult result) {
		String client = uploadModel.getClient();
		String protocol = uploadModel.getProtocol();
		List<MultipartFile> files = uploadModel.getFiles();
		
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
		
		return "redirect:/upload";
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