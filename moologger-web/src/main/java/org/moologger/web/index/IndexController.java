package org.moologger.web.index;

import com.google.common.collect.Sets;
import org.moologger.core.model.Conversation;
import org.moologger.core.repository.ConversationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.SortedSet;

@Controller
@RequestMapping("/")
public class IndexController {

    private ConversationRepository conversationRepository;

    @Autowired
    public IndexController(ConversationRepository conversationRepository) {
        this.conversationRepository = conversationRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String get(Model model, @ModelAttribute("conversations") List<Conversation> conversations) {
        model.addAttribute(conversations);

        return "index";
    }

    @ModelAttribute("conversations")
    public SortedSet<Conversation> getConversations() {
        return Sets.newTreeSet(conversationRepository.findAll());
    }

}