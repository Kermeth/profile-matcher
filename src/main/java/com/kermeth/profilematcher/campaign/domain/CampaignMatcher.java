package com.kermeth.profilematcher.campaign.domain;

import com.kermeth.profilematcher.profile.domain.PlayerProfile;

public interface CampaignMatcher {
    boolean matches(PlayerProfile profile);
}
