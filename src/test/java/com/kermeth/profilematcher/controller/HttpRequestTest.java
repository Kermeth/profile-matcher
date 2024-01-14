package com.kermeth.profilematcher.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest
@AutoConfigureWebTestClient
class HttpRequestTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void shouldReturnPlayerProfile() {

        var playerId = "97983be2-98b7-11e7-90cf-082e5f28d836";

        webTestClient.get()
                .uri("/get_client_config/" + playerId)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.playerId").isEqualTo(playerId)
                .jsonPath("$.campaigns[0]").isEqualTo("mycampaign");

    }
}
