package com.kermeth.profilematcher.campaign;

import com.kermeth.profilematcher.profile.domain.PlayerProfile;

public interface CampaignMatcher {
    boolean matches(PlayerProfile profile);
}
