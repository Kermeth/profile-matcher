package com.kermeth.profilematcher.profile.application;

import com.kermeth.profilematcher.profile.domain.PlayerProfile;

public interface PlayerProfileRepository {

    public PlayerProfile getProfile(String playerId);

}
