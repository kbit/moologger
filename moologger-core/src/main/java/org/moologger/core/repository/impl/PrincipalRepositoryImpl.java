package org.moologger.core.repository.impl;

import org.moologger.core.model.Alias;
import org.moologger.core.MoologgerCoreUtil;
import org.moologger.core.model.Principal;
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

    @Override
    public Principal findOneByAliasKey(String key) {
        String client = MoologgerCoreUtil.getClient(key);
        String protocol = MoologgerCoreUtil.getProtocol(key);
        String identifier = MoologgerCoreUtil.getIdentifier(key);

        return mongoTemplate.findOne(query(where("aliases").elemMatch(where("client").is(client).and("protocol").is(protocol).and("identifier").is(identifier))), Principal.class);
    }

    @Override
    public Alias findOneAliasByAliasKey(String key) {
        String client = MoologgerCoreUtil.getClient(key);
        String protocol = MoologgerCoreUtil.getProtocol(key);
        String identifier = MoologgerCoreUtil.getIdentifier(key);

        return mongoTemplate.findOne(query(where("client").is(client).and("protocol").is(protocol).and("identifier").is(identifier)), Alias.class);
    }

}