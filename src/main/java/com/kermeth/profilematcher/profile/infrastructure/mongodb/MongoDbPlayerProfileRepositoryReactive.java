package com.kermeth.profilematcher.profile.infrastructure.mongodb;

import com.kermeth.profilematcher.profile.application.PlayerProfileRepositoryReactive;
import com.kermeth.profilematcher.profile.domain.Clan;
import com.kermeth.profilematcher.profile.domain.Device;
import com.kermeth.profilematcher.profile.domain.PlayerProfile;
import com.kermeth.profilematcher.utils.DateUtils;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

@Qualifier("mongoDbRepository")
@Repository
public class MongoDbPlayerProfileRepositoryReactive implements PlayerProfileRepositoryReactive {

    private Logger logger = Logger.getLogger(this.getClass().getName());

    final ReactiveMongoTemplate mongoTemplate;

    @PostConstruct
    public void init() {
        // Populate the database with some data
        var player = new PlayerProfileDocument(
                "97983be2-98b7-11e7-90cf-082e5f28d836",
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

        getProfile(player.getPlayerId())
                .doOnNext(playerProfile -> logger.info("Found profile for player " + playerProfile.playerId()))
                .switchIfEmpty(save(player).map(this::mapPlayerProfile))
                .doOnNext(playerProfile -> logger.info("Saved profile for player " + playerProfile.playerId()))
                .block();
    }

    public MongoDbPlayerProfileRepositoryReactive(ReactiveMongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public Mono<PlayerProfileDocument> save(PlayerProfileDocument playerProfileDocument) {
        return mongoTemplate.save(playerProfileDocument);
    }

    @Override
    public Mono<PlayerProfile> getProfile(String playerId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("player_id").is(playerId));
        return mongoTemplate.findOne(query, PlayerProfileDocument.class)
                .map(this::mapPlayerProfile);
    }

    private PlayerProfile mapPlayerProfile(PlayerProfileDocument playerProfileDocument) {
        return new PlayerProfile(
                UUID.fromString(playerProfileDocument.getPlayerId()),
                playerProfileDocument.getCredential(),
                DateUtils.parseInstant(playerProfileDocument.getCreated()),
                DateUtils.parseInstant(playerProfileDocument.getModified()),
                DateUtils.parseInstant(playerProfileDocument.getLastSession()),
                playerProfileDocument.getTotalSpent(),
                playerProfileDocument.getTotalRefund(),
                playerProfileDocument.getTotalTransactions(),
                DateUtils.parseInstant(playerProfileDocument.getLastPurchase()),
                playerProfileDocument.getActiveCampaigns(),
                playerProfileDocument.getDevices(),
                playerProfileDocument.getLevel(),
                playerProfileDocument.getXp(),
                playerProfileDocument.getTotalPlaytime(),
                playerProfileDocument.getCountry(),
                playerProfileDocument.getLanguage(),
                DateUtils.parseInstant(playerProfileDocument.getBirthdate()),
                playerProfileDocument.getGender(),
                playerProfileDocument.getInventory(),
                playerProfileDocument.getClan(),
                playerProfileDocument.getCustomFields()
        );
    }
}
