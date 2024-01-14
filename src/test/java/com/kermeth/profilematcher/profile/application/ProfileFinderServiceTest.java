package com.kermeth.profilematcher.profile.application;

import com.kermeth.profilematcher.profile.domain.Clan;
import com.kermeth.profilematcher.profile.domain.Device;
import com.kermeth.profilematcher.profile.domain.PlayerProfile;
import com.kermeth.profilematcher.utils.DateUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

@ExtendWith(MockitoExtension.class)
class ProfileFinderServiceTest {

    @Test
    void shouldReturnPlayerProfile(@Mock PlayerProfileRepositoryReactive playerProfileRepository) {
        // Given
        var playerId = "97983be2-98b7-11e7-90cf-082e5f28d836";
        var player = new PlayerProfile(
                UUID.fromString(playerId),
                "apple_credential",
                DateUtils.parseInstant("2021-01-10 13:37:17Z"),
                DateUtils.parseInstant("2021-01-23 13:37:17Z"),
                DateUtils.parseInstant("2021-01-23 13:37:17Z"),
                400,
                0,
                5,
                DateUtils.parseInstant("2021-01-22 13:37:17Z"),
                List.of(),
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

        Mockito.when(playerProfileRepository.getProfile(playerId))
                .thenReturn(Mono.just(player));

        try {
            // When
            var profileFinderService = new ProfileFinderService(playerProfileRepository);
            var profile = profileFinderService.getProfile(playerId)
                .block();
            // Then
            assertTrue(profile != null && playerId.equals(profile.playerId().toString()));
        } catch (Exception e) {
            fail(e.getMessage());
        }

    }


}
