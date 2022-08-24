package com.laurentiuspilca.ssia;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.mockJwt;

@SpringBootTest
@AutoConfigureWebTestClient
class EndpointTests {

    @Autowired
    private WebTestClient client;

    @Test
    @DisplayName("When calling the /hello endpoint without a user, " +
            "the application should return HTTP 401 Unauthorized.")
    void testCallHelloWithoutUser() {
        client.get()
                .uri("/hello")
                .exchange()
                .expectStatus().isUnauthorized();
    }

    @Test
    @DisplayName("When calling the /hello endpoint with a user, " +
            "the application should return HTTP 200 OK.")
    void testCallHelloWithUser() {
        client.mutateWith(mockJwt())
                .get()
                .uri("/hello")
                .exchange()
                .expectStatus().isOk();
    }
}
