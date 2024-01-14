package com.kermeth.profilematcher.campaign;

import com.kermeth.profilematcher.profile.domain.PlayerProfile;

import java.util.List;

public record Matchers(
    LevelMatcher level,
    List<CampaignMatcher> has,
    List<CampaignMatcher> doesNotHave
) {

    public boolean matches(PlayerProfile profile) {
        return level.matches(profile) &&
            has.stream().allMatch(matcher -> matcher.matches(profile)) &&
            doesNotHave.stream().noneMatch(matcher -> matcher.matches(profile));
    }
}
