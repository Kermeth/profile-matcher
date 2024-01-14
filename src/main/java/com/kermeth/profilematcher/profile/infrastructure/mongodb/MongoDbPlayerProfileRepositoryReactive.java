package com.kermeth.profilematcher.profile.infrastructure.mongodb;

import com.kermeth.profilematcher.profile.application.PlayerProfileRepository;
import com.kermeth.profilematcher.profile.domain.PlayerProfile;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MongoDbPlayerProfileRepository implements PlayerProfileRepository {

    final ReactiveMongoTemplate mongoTemplate;

    public MongoDbPlayerProfileRepository(ReactiveMongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public PlayerProfile getProfile(String playerId) {
        return mongoTemplate.findById(playerId, PlayerProfile.class)
                .map(playerProfile -> {
                    System.out.println("Found profile: " + playerProfile);
                    return playerProfile;
                })
    }
}
