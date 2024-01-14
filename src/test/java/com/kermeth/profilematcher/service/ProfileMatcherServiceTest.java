package com.kermeth.profilematcher.service;

import com.kermeth.profilematcher.campaign.application.CampaignFinderService;
import com.kermeth.profilematcher.campaign.domain.*;
import com.kermeth.profilematcher.profile.application.ProfileFinderService;
import com.kermeth.profilematcher.profile.domain.Clan;
import com.kermeth.profilematcher.profile.domain.Device;
import com.kermeth.profilematcher.profile.domain.PlayerProfile;
import com.kermeth.profilematcher.profile.infrastructure.mongodb.MongoDbPlayerProfileRepositoryReactive;
import com.kermeth.profilematcher.utils.DateUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProfileMatcherServiceTest {

    @Test
    void shouldReturnProfile(
            @Mock CampaignFinderService campaignFinderService,
            @Mock ProfileFinderService profileFinderService,
            @Mock MongoDbPlayerProfileRepositoryReactive playerRepository
            ) {
        // Given
        var playerId = "97983be2-98b7-11e7-90cf-082e5f28d836";
        final var mockCampaign = new Campaign(
                "mygame",
                "mycampaign",
                10.5f,
                DateUtils.parseInstant("2022-01-25 00:00:00Z"),
                DateUtils.parseInstant("2022-02-25 00:00:00Z"),
                true,
                DateUtils.parseInstant("2021-07-13 11:46:58Z"),
                new Matchers(
                        new LevelMatcher(1,3),
                        List.of(
                                new CountryMatcher(List.of("CA", "US", "RO")),
                                new ItemMatcher(List.of("item_1"))
                        ),
                        List.of(
                                new ItemMatcher(List.of("item_4"))
                        )
                )
        );
        var mockProfile = new PlayerProfile(
                UUID.fromString(playerId),
                "apple_credential",
                DateUtils.parseInstant("2021-01-10 13:37:17Z"),
                DateUtils.parseInstant("2021-01-23 13:37:17Z"),
                DateUtils.parseInstant("2021-01-23 13:37:17Z"),
                400,
                0,
                5,
                DateUtils.parseInstant("2021-01-22 13:37:17Z"),
                new ArrayList<>(),
                List.of(
                        new Device(
                                1,
                                "apple iphone 11",
                                "vodafone",
                                "123"
                        )
                ),
                3,
                1000,
                144,
                "CA",
                "fr",
                DateUtils.parseInstant("2000-01-10 13:37:17Z"),
                "male",
                Map.of("cash", 123, "coins", 123, "item_1", 1, "item_34", 3, "itme_55", 2),
                new Clan(
                        "123456",
                        "Hello world clan"
                ),
                Map.of("_customfield", "mycustom")
        );
        when(campaignFinderService.findCurrentCampaign())
                .thenReturn(Mono.just(mockCampaign));
        when(playerRepository.getProfile(playerId))
                .thenReturn(Mono.just(mockProfile));

        // When
        var profileMatcherService = new ProfileMatcherService(campaignFinderService, playerRepository);
        var response = profileMatcherService.getProfileWithCampaigns(playerId)
                .block();

        // Then
        assert response != null;
        assertTrue(response.activeCampaigns() != null && response.activeCampaigns().size() == 1);
    }

}