package com.kermeth.profilematcher.campaign.application;

import com.kermeth.profilematcher.campaign.domain.LevelMatcher;
import com.kermeth.profilematcher.campaign.infrastructure.ApiMatchers;
import com.kermeth.profilematcher.campaign.infrastructure.CampaignApiResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CampaignFinderServiceTest {
    @Test
    void shouldReturnCampaign(@Mock WebClient webClient) {

        // Given
        final var uriSpecMock = Mockito.mock(WebClient.RequestHeadersUriSpec.class);
        final var headersSpecMock = Mockito.mock(WebClient.RequestHeadersSpec.class);
        final var responseSpecMock = Mockito.mock(WebClient.ResponseSpec.class);
        final var mockCampaignResponse = new CampaignApiResponse(
                "mygame",
                "mycampaign",
                10.5f,
                "2022-01-25 00:00:00Z",
                "2022-02-25 00:00:00Z",
                true,
                "2021-07-13 11:46:58Z",
                new ApiMatchers(
                        new LevelMatcher(1,3),
                        Map.of(
                                "country", List.of("CA", "US", "RO"),
                                "items", List.of("item_1")
                        ),
                        Map.of(
                                "items", List.of("item_4")
                        )
                )
        );

        when(webClient.get())
                .thenReturn(uriSpecMock);
        when(uriSpecMock.uri("/campaigns/current"))
                .thenReturn(headersSpecMock);
        when(headersSpecMock.retrieve())
                .thenReturn(responseSpecMock);
        when(responseSpecMock.bodyToMono(CampaignApiResponse.class))
                .thenReturn(Mono.just(mockCampaignResponse));

        // When
        var campaignFinder = new CampaignFinderService(webClient);
        var campaign = campaignFinder.findCurrentCampaign().block();

        // Then
        assert campaign != null;
        assertEquals("mycampaign", campaign.name());

    }

}