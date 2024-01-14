package com.kermeth.profilematcher.campaign;

import com.kermeth.profilematcher.profile.domain.PlayerProfile;

public class LevelMatcher implements CampaignMatcher {

    private final int min;
    private final int max;

    public LevelMatcher(int min, int max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public boolean matches(PlayerProfile profile) {
        return profile.level() >= min && profile.level() <= max;
    }
}
