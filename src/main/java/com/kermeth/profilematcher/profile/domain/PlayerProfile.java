package com.kermeth.profilematcher.model.player.profile;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.kermeth.profilematcher.model.campaign.Campaign;
import com.kermeth.profilematcher.model.Clan;

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
        List<Campaign> activeCampaigns,
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
