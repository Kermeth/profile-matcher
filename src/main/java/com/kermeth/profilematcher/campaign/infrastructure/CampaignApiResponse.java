package com.kermeth.profilematcher.campaign.infrastructure;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CampaignApiResponse(
        @JsonProperty("game")
        String game,
        @JsonProperty("name")
        String name,
        @JsonProperty("priority")
        Float priority,
        @JsonProperty("start_date")
        String startDate,
        @JsonProperty("end_date")
        String endDate,
        @JsonProperty("enabled")
        Boolean enabled,
        @JsonProperty("last_updated")
        String lastUpdate,
        @JsonProperty("matchers")
        ApiMatchers matchers
) {
}
