package com.kermeth.profilematcher.campaign.domain;

import com.kermeth.profilematcher.profile.domain.PlayerProfile;

import java.util.List;

public class CountryMatcher implements CampaignMatcher {

    private final List<String> countries;

    public CountryMatcher(List<String> countries) {
        this.countries = countries;
    }

    @Override
    public boolean matches(PlayerProfile profile) {
        return countries.stream().anyMatch(profile.country()::equalsIgnoreCase);
    }
}
