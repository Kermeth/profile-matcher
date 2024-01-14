package com.kermeth.profilematcher.profile.infrastructure;

import com.kermeth.profilematcher.profile.domain.Clan;
import com.kermeth.profilematcher.profile.domain.Device;
import com.kermeth.profilematcher.profile.infrastructure.mongodb.MongoDbPlayerProfileRepositoryReactive;
import com.kermeth.profilematcher.profile.infrastructure.mongodb.PlayerProfileDocument;
import com.mongodb.reactivestreams.client.MongoClients;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

class MongoDbPlayerProfileRepositoryTest {

    ReactiveMongoTemplate mongoTemplate;

    @Test
    void shouldFindPlayerProfile() {
        // Given
        var mongoClient = MongoClients.create("mongodb://localhost:27017");
        this.mongoTemplate = new ReactiveMongoTemplate(mongoClient, "playerProfiles");
        var repository = new MongoDbPlayerProfileRepositoryReactive(mongoTemplate);
        var playerId = "97983be2-98b7-11e7-90cf-082e5f28d836";

        // Prepare data
        var player = new PlayerProfileDocument(
                playerId,
                "apple_credential",
                "2021-01-10 13:37:17Z",
                "2021-01-23 13:37:17Z",
                "2021-01-23 13:37:17Z",
                400,
                0,
                5,
                "2021-01-22 13:37:17Z",
                List.of(),
                List.of(
                        new Device(
                                1,
                                "apple iphone 11",
                                "vodafone",
                                "123"
                        )
                ),
                3,
                1000,
                144,
                "CA",
                "fr",
                "2000-01-10 13:37:17Z",
                "male",
                Map.of("cash", 123, "coins", 123, "item_1", 1, "item_34", 3, "itme_55", 2),
                new Clan(
                        "123456",
                        "Hello world clan"
                ),
                Map.of("_customfield", "mycustom")
        );
        repository.save(player).block();

        // When
        var playerProfile = repository.getProfile(playerId)
                .doOnNext(System.out::println)
                .block();

        // Then
        assertTrue(playerProfile != null && playerId.equals(playerProfile.playerId().toString()));
    }

    @AfterAll
    static void tearDown() {
        var mongoClient = MongoClients.create("mongodb://localhost:27017");
        var mongoTemplate = new ReactiveMongoTemplate(mongoClient, "playerProfiles");
        mongoTemplate.dropCollection(PlayerProfileDocument.class).block();
    }

}
