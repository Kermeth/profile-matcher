package com.kermeth.profilematcher.campaign;

import com.kermeth.profilematcher.profile.domain.PlayerProfile;

import java.time.Instant;

public record Campaign(
        String game,
        String name,
        Float priority,
        Instant startDate,
        Instant endDate,
        Boolean enabled,
        Instant lastUpdate,
        Matchers matchers
) {

    public boolean matches(PlayerProfile profile) {
        return matchers.matches(profile);
    }
}
