package com.kermeth.profilematcher.profile.application;

import com.kermeth.profilematcher.profile.domain.PlayerProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

public class ProfileFinderService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private final PlayerProfileRepositoryReactive playerProfileRepositoryReactive;

    public ProfileFinderService(PlayerProfileRepositoryReactive playerProfileRepositoryReactive) {
        this.playerProfileRepositoryReactive = playerProfileRepositoryReactive;
    }

    public Mono<PlayerProfile> getProfile(String playerId) {
        logger.debug("Getting profile for player {}", playerId);
        return playerProfileRepositoryReactive.getProfile(playerId)
                .doOnNext(playerProfile -> logger.debug("Found profile for player {}", playerId))
                .switchIfEmpty(Mono.error(new PlayerProfileNotFoundException(playerId)));
    }
}
