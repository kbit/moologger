package org.moologger.core.repository;

import org.moologger.core.model.Conversation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConversationRepository extends MongoRepository<Conversation, String>, ConversationRepositoryCustom {}