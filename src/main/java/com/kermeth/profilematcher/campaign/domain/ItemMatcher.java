package com.kermeth.profilematcher.campaign;

import com.kermeth.profilematcher.profile.domain.PlayerProfile;

import java.util.List;

public class ItemMatcher implements CampaignMatcher{

    List<String> items;

    public ItemMatcher(List<String> items) {
        this.items = items;
    }
    @Override
    public boolean matches(PlayerProfile profile) {
        return profile.inventory()
                .keySet()
                .stream()
                .anyMatch(items::contains);
    }
}
