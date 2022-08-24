package com.laurentiuspilca.ssia;

import com.laurentiuspilca.ssia.services.NameService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MainTests {

    @Autowired
    private NameService nameService;

    @Test
    @DisplayName("When the method is called without a user," +
            " it throws AuthenticationException")
    void testNameServiceWithNoUser() {
        assertThrows(AuthenticationException.class,
                () -> nameService.getSecretNames("john"));
    }

    @Test
    @DisplayName("When the method is called with a different username parameter than the authenticated user, " +
            "it should throw AccessDeniedException.")
    @WithMockUser(username = "bill")
    void testNameServiceCallingTheSecretNamesMethodWithDifferentUser() {
        assertThrows(AccessDeniedException.class,
                () -> nameService.getSecretNames("emma"));
    }

    @Test
    @DisplayName("When the method is called for the authenticated user, " +
            "it should return the expected result.")
    @WithMockUser(username = "emma")
    void testNameServiceCallingTheSecretNamesMethodWithAuthenticatedUser() {
        var result = nameService.getSecretNames("emma");

        assertEquals(List.of("Fantastico"), result);
    }
}
