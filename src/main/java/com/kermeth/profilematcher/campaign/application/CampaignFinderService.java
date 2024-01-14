package com.kermeth.profilematcher.campaign.application;

import com.kermeth.profilematcher.campaign.domain.*;
import com.kermeth.profilematcher.campaign.infrastructure.CampaignApiResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Service
public class CampaignFinderService {

    private final WebClient webClient;

    public CampaignFinderService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<Campaign> findCurrentCampaign(){
        return webClient.get()
                .uri("/campaigns/current")
                .retrieve()
                .bodyToMono(CampaignApiResponse.class)
                .map(this::mapApiResponseToDomain);
    }

    private Campaign mapApiResponseToDomain(CampaignApiResponse campaignApiResponse) {
        return new Campaign(
                campaignApiResponse.game(),
                campaignApiResponse.name(),
                campaignApiResponse.priority(),
                parseInstant(campaignApiResponse.startDate()),
                parseInstant(campaignApiResponse.endDate()),
                campaignApiResponse.enabled(),
                parseInstant(campaignApiResponse.lastUpdate()),
                new Matchers(
                        campaignApiResponse.matchers().level(),
                        mapMatcher(campaignApiResponse.matchers().has()),
                        mapMatcher(campaignApiResponse.matchers().doesNotHave())
                )
        );
    }

    private List<CampaignMatcher> mapMatcher(Map<String, List<String>> matcher) {
        return matcher.entrySet()
                .stream()
                .map(entry ->
                    switch (entry.getKey()) {
                        case "country" -> new CountryMatcher(entry.getValue());
                        case "items" -> new ItemMatcher(entry.getValue());
                        default -> throw new IllegalArgumentException("Unknown property matcher: " + entry.getKey());
                })
                .toList();
    }

    private Instant parseInstant(String date) {
        var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss'Z'").withZone(ZoneId.of("UTC"));
        return ZonedDateTime.parse(date, formatter).toInstant();
    }
}
