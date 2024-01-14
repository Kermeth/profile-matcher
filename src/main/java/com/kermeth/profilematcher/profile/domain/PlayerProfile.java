package com.kermeth.profilematcher.profile.domain;

import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public record PlayerProfile(
        UUID playerId,
        String credential,
        Instant created,
        Instant modified,
        Instant lastSession,
        Integer totalSpent,
        Integer totalRefund,
        Integer totalTransactions,
        Instant lastPurchase,
        List<String> activeCampaigns,
        List<Device> devices,
        Integer level,
        Integer xp,
        Integer totalPlaytime,
        String country,
        String language,
        Instant birthdate,
        String gender,
        Map<String,Integer> inventory,
        Clan clan,
        @JsonAnySetter
        Map<String,Object> customFields
) {
}
