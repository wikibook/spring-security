package com.laurentiuspilca.ssia;

import com.laurentiuspilca.ssia.controllers.HelloController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.test.context.support.WithMockUser;
import reactor.test.StepVerifier;

@SpringBootTest
class MethodTests {

    @Autowired
    private HelloController helloController;

    @Test
    @DisplayName("When calling the hello() method a user, " +
            "the method should throw an AccessDeniedException.")
    void testCallHelloWithoutUser() {
        StepVerifier.create(helloController.hello())
                .expectError(AccessDeniedException.class)
                .verify();
    }

    @Test
    @DisplayName("When calling the hello() method a user that doesn't have ADMIN role, " +
            "the method should throw an AccessDeniedException.")
    @WithMockUser
    void testCallHelloWithUserButWrongRole() {
        StepVerifier.create(helloController.hello())
                .expectError(AccessDeniedException.class)
                .verify();
    }

    @Test
    @DisplayName("When calling the hello() method a user having role ADMIN, " +
            "the method should return the expected result.")
    @WithMockUser(roles = "ADMIN")
    void testCallHelloWithUserAndAdminRole() {
        StepVerifier.create(helloController.hello())
                .expectNext("Hello!")
                .verifyComplete();
    }
}
