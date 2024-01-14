package com.kermeth.profilematcher.profile.infrastructure;

import com.kermeth.profilematcher.profile.domain.PlayerProfile;
import com.kermeth.profilematcher.service.ProfileFinderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProfileMatcherController {

    private final ProfileFinderService profileFinderService;

    public ProfileMatcherController(ProfileFinderService profileFinderService) {
        this.profileFinderService = profileFinderService;
    }

    @GetMapping("/get_client_config/{playerId}")
    public PlayerProfile getProfile(@PathVariable String playerId) {
        return profileFinderService.getProfile(playerId);
    }

}
