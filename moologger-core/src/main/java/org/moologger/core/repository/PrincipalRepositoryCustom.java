package org.moologger.core.repository;

import org.moologger.core.model.Alias;
import org.moologger.core.model.Principal;

public interface PrincipalRepositoryCustom {

    boolean existsByIdentifier(String identifier);

    Principal findOneByAliasKey(String key);

    Alias findOneAliasByAliasKey(String key);

}