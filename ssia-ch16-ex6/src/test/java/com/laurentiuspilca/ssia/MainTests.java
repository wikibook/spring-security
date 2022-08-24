package com.laurentiuspilca.ssia;

import com.laurentiuspilca.ssia.services.NameService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.test.context.support.WithMockUser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class MainTests {

    @Autowired
    private NameService nameService;

    @Test
    @DisplayName("When the method is called without a user, " +
            "it throws AuthenticationException")
    void testNameServiceWithNoUser() {
        assertThrows(AuthenticationException.class,
                () -> nameService.getName());
    }

    @Test
    @DisplayName("When the method is called with a user having the wrong role, " +
            "it throws AccessDeniedException")
    @WithMockUser(username = "mary", roles = "MANAGER")
    void testNameServiceWithUserHavingWrongRole() {
        assertThrows(AccessDeniedException.class,
                () -> nameService.getName());
    }

    @Test
    @DisplayName("When the method is called with a user having tole admin, " +
            "it returns successfully.")
    @WithMockUser(username = "mary", roles = "ADMIN")
    void testNameServiceWithUserHavingCorrectRole() {
        var result = nameService.getName();

        assertEquals("Fantastico", result);
    }

}
