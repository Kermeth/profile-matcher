package com.kermeth.profilematcher.campaign.infrastructure;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kermeth.profilematcher.campaign.domain.LevelMatcher;

import java.util.List;
import java.util.Map;

public record ApiMatchers(
        @JsonProperty("level")
        LevelMatcher level,
        @JsonProperty("has")
        Map<String, List<String>> has,
        @JsonProperty("does_not_have")
        Map<String, List<String>> doesNotHave
) {
}
