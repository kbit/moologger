package org.moologger.config;

import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@ComponentScan("org.moologger.core")
@EnableMongoRepositories("org.moologger.core")
public class MoologgerCoreConfig {

    @Bean
    public MongoClient mongoClient() throws Exception {
        return new MongoClient();
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongoClient(), "moologger");
    }

}