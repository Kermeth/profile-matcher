package com.kermeth.profilematcher.profile.infrastructure;

import com.kermeth.profilematcher.profile.application.PlayerProfileRepositoryReactive;
import com.kermeth.profilematcher.profile.domain.Clan;
import com.kermeth.profilematcher.profile.domain.Device;
import com.kermeth.profilematcher.profile.domain.PlayerProfile;
import com.kermeth.profilematcher.utils.DateUtils;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Qualifier("inMemoryRepository")
@Repository
public class InMemoryPlayerProfileRepositoryReactive implements PlayerProfileRepositoryReactive {

    private final Map<String, PlayerProfile> database = new HashMap<>();

    @PostConstruct
    private void init(){
        // Mock data
        var profile1 = new PlayerProfile(
                UUID.fromString("97983be2-98b7-11e7-90cf-082e5f28d836"),
                "apple_credential",
                DateUtils.parseInstant("2021-01-10 13:37:17Z"),
                DateUtils.parseInstant("2021-01-23 13:37:17Z"),
                DateUtils.parseInstant("2021-01-23 13:37:17Z"),
                400,
                0,
                5,
                DateUtils.parseInstant("2021-01-22 13:37:17Z"),
                List.of(),
                List.of(new Device(
                        1,
                        "apple iphone 11",
                        "vodafone",
                        "123"
                )),
                3,
                1000,
                144,
                "CA",
                "fr",
                DateUtils.parseInstant("2000-01-10 13:37:17Z"),
                "male",
                Map.of(
                        "cash", 123,
                        "coins", 123,
                        "item_1", 1,
                        "item_34", 3,
                        "item_55", 2
                ),
                new Clan(
                        "123456",
                        "Hello world clan"
                ),
                Map.of(
                        "_customfield", "mycustom"
                )
        );
        database.put(profile1.playerId().toString(), profile1);
    }

    @Override
    public Mono<PlayerProfile> getProfile(String playerId) {
        return Mono.justOrEmpty(database.get(playerId))
                .switchIfEmpty(Mono.error(() -> new RuntimeException("Player not found")));
    }
}
