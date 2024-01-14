package com.kermeth.profilematcher.service;

import com.kermeth.profilematcher.profile.application.PlayerProfileRepository;
import com.kermeth.profilematcher.profile.domain.PlayerProfile;
import org.springframework.stereotype.Service;

@Service
public class ProfileFinderService {

    private final PlayerProfileRepository playerProfileRepository;

    public ProfileFinderService(PlayerProfileRepository playerProfileRepository) {
        this.playerProfileRepository = playerProfileRepository;
    }

    public PlayerProfile getProfile(String playerId) {
        return null;
    }
}
