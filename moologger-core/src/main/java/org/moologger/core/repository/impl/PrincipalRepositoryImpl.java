package org.moologger.core.repository.impl;

import org.moologger.core.Principal;
import org.moologger.core.repository.PrincipalRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

public class PrincipalRepositoryImpl implements PrincipalRepositoryCustom {

    private MongoTemplate mongoTemplate;

    @Autowired
    public PrincipalRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public boolean existsByIdentifier(String identifier) {
        return mongoTemplate.count(query(where("identifier").is(identifier)), Principal.class) > 0;
    }

}