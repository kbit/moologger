package org.moologger.core.repository;

import org.moologger.core.Principal;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PrincipalRepository extends MongoRepository<Principal, String>, PrincipalRepositoryCustom {}