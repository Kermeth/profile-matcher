package com.kermeth.profilematcher.profile.application;

import com.kermeth.profilematcher.profile.infrastructure.mongodb.MongoDbPlayerProfileRepositoryReactive;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
class ProfileFinderServiceTest {

    // TODO mock repository
    @Autowired
    private MongoDbPlayerProfileRepositoryReactive playerProfileRepository;

    @Test
    void shouldReturnPlayerProfile() {
        // Given
        var playerId = "97983be2-98b7-11e7-90cf-082e5f28d836";
        var profileFinderService = new ProfileFinderService(playerProfileRepository);

        try {
            // When
            var profile = profileFinderService.getProfile(playerId)
                .block();
            // Then
            assertTrue(profile != null && playerId.equals(profile.playerId().toString()));
        } catch (Exception e) {
            fail(e.getMessage());
        }

    }


}
