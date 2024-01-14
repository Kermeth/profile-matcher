package com.kermeth.profilematcher.profile.application;

import com.kermeth.profilematcher.profile.domain.PlayerProfile;
import reactor.core.publisher.Mono;

public interface PlayerProfileRepositoryReactive {

    Mono<PlayerProfile> getProfile(String playerId);

}
