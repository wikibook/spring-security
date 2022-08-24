package com.laurentiuspilca.ssia;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;

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
    @DisplayName("When calling the /hello endpoint with a user wihtout ADMIN role, " +
            "the application should return HTTP 403 Forbidden.")
    @WithMockUser
    void testCallHelloWithUserButNotAValidRole() {
        client.get()
                .uri("/hello")
                .exchange()
                .expectStatus().isForbidden();
    }

    @Test
    @DisplayName("When calling the /hello endpoint with a user with ADMIN role, " +
            "the application should return HTTP 200 OK.")
    @WithMockUser(roles = "ADMIN")
    void testCallHelloWithUserAndValidRole() {
        client.get()
                .uri("/hello")
                .exchange()
                .expectStatus().isOk();
    }
}
