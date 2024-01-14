package com.kermeth.profilematcher.profile.infrastructure.mongodb;

import com.mongodb.reactivestreams.client.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

@Configuration
public class MongoDbConfig {

    final MongoClient mongoClient;

    public MongoDbConfig(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

}
