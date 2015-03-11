package org.moologger.core.repository;

import org.moologger.core.model.Alias;

import java.util.List;

public interface ConversationRepositoryCustom {

    List<Alias> generateAliases();

}