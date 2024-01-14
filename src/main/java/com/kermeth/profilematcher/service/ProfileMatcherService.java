package com.kermeth.profilematcher.service;

import com.kermeth.profilematcher.campaign.application.CampaignFinderService;
import com.kermeth.profilematcher.profile.application.PlayerProfileNotFoundException;
import com.kermeth.profilematcher.profile.application.ProfileFinderService;
import com.kermeth.profilematcher.profile.domain.PlayerProfile;
import com.kermeth.profilematcher.profile.infrastructure.mongodb.MongoDbPlayerProfileRepositoryReactive;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@Service
public class ProfileMatcherService {

    private final CampaignFinderService campaignFinderService;

    private final ProfileFinderService profileFinderService;

    public ProfileMatcherService(CampaignFinderService campaignFinderService, MongoDbPlayerProfileRepositoryReactive playerRepository) {
        this.campaignFinderService = campaignFinderService;
        // Use MongoDbPlayerProfileRepository as the implementation of PlayerProfileRepositoryReactive,
        // but we could use any other implementation of PlayerProfileRepositoryReactive
        this.profileFinderService = new ProfileFinderService(playerRepository);
    }

    public Mono<PlayerProfile> getProfileWithCampaigns(String playerId) {

        // Search for the profile rise exception if not found
        var profile = profileFinderService.getProfile(playerId)
                .onErrorMap(throwable -> {
                    if(throwable instanceof PlayerProfileNotFoundException) {
                        return new ResponseStatusException(HttpStatus.NOT_FOUND, "Player profile not found", throwable);
                    } else {
                        return new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error", throwable);
                    }
                });

        // Search for the current campaign from an external API
        var currentCampaign = campaignFinderService.findCurrentCampaign();

        // Zip requests in parallel virtual threads
        return Mono.zip(profile, currentCampaign)
                .map(tuple -> {
                    var playerProfile = tuple.getT1();
                    var campaign = tuple.getT2();
                    if(campaign.matches(playerProfile)){
                        playerProfile.activeCampaigns().add(campaign.name());
                    }
                    return playerProfile;
                });
    }

}
