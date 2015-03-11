package org.moologger.core.repository.impl;

import org.moologger.core.repository.ConversationRepositoryCustom;
import org.moologger.core.model.Alias;
import org.moologger.core.model.Conversation;
import org.moologger.core.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ConversationRepositoryImpl implements ConversationRepositoryCustom {

    private MongoTemplate mongoTemplate;

    @Autowired
    public ConversationRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<Alias> generateAliases() {
        Set<Alias> aliases = new HashSet<>();

        List<Conversation> conversations = mongoTemplate.findAll(Conversation.class);

        for (Conversation conversation : conversations) {
            List<Message> messages = conversation.getMessages();

            for (Message message : messages) {
                Alias alias = new Alias();
                alias.setClient(conversation.getClient());
                alias.setProtocol(conversation.getProtocol());
                alias.setIdentifier(message.getAlias());

                aliases.add(alias);
            }
        }

        return new ArrayList<>(aliases);
    }

}