package org.moologger.core.dao;

import org.moologger.core.Conversation;
import org.moologger.core.Message;

public interface ConversationDAO {
	
	void addMessage(Conversation conversation, Message message);
	
	void deleteMessage(Conversation conversation, Message message);

}