package org.moologger.web.index;

import org.moologger.core.repository.ConversationRepository;
import org.moologger.web.conversation.ConversationModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

@Controller
@RequestMapping("/")
public class IndexController {

    @Resource
    private ConversationRepository conversationRepository;

    @RequestMapping(method = RequestMethod.GET)
    public String get(Model model) {
        model.addAttribute("conversations", conversationRepository.findAll());
        model.addAttribute("command", new ConversationModel());

        return "index";
    }

}