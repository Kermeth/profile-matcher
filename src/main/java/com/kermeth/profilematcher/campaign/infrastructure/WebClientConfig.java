package com.kermeth.profilematcher.campaign.infrastructure;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${campaign-service.url}")
    private String campaignServiceUrl;

    @Value("${campaign-service.port}")
    private String campaignServicePort;

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl(campaignServiceUrl + ":" + campaignServicePort)
                .build();
    }
}
