package com.kermeth.profilematcher.controller;

import com.kermeth.profilematcher.profile.domain.PlayerProfile;
import com.kermeth.profilematcher.service.ProfileMatcherService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class ProfileMatcherController {

    private final ProfileMatcherService profileMatcherService;

    public ProfileMatcherController(ProfileMatcherService profileMatcherService) {
        this.profileMatcherService = profileMatcherService;
    }

    @GetMapping("/get_client_config/{playerId}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<PlayerProfile> getProfile(@PathVariable String playerId) {
        return profileMatcherService.getProfileWithCampaigns(playerId);
    }

}
