package org.moologger.core.repository.impl;

import org.moologger.core.Principal;
import org.moologger.core.repository.PrincipalRepositoryCustom;
import org.springframework.data.mongodb.core.MongoTemplate;

import javax.annotation.Resource;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

public class PrincipalRepositoryImpl implements PrincipalRepositoryCustom {

    @Resource
    MongoTemplate mongoTemplate;

    @Override
    public boolean existsByIdentifier(String identifier) {
        return mongoTemplate.count(query(where("identifier").is(identifier)), Principal.class) > 0;
    }

}