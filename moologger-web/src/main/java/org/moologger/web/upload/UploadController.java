package org.moologger.web.upload;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.moologger.core.Log;
import org.moologger.core.dao.MoologgerService;
import org.moologger.core.parser.ParserException;
import org.moologger.core.parser.registry.impl.ParserRegistryImpl;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes
@RequestMapping("/uploads")
public class UploadController {
	
	@Resource
	private ParserRegistryImpl parserRegistry;
	
	@Resource
	private MoologgerService moologgerService;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView showUploads() {
		return new ModelAndView("upload", "command", new Upload());
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String upload(@ModelAttribute("upload") Upload upload, BindingResult result) {
		List<MultipartFile> files = upload.getFiles();
		
		try {
			for (MultipartFile file : files) {
				Log newLog = getParserRegistry().getParser("Pidgin", "Oscar").parse(file.getInputStream());
				
				getMoologgerService().saveLog(newLog);
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (ParserException pe) {
			pe.printStackTrace();
		}
		
		return "redirect:/uploads";
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