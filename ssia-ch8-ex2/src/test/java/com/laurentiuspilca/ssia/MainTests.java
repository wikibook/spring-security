package com.laurentiuspilca.ssia;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MainTests {

    @Autowired
    private MockMvc mvc;

    @Test
    @DisplayName("Endpoints cannot be called unauthenticated")
    public void testFailedAuthentication() throws Exception {
        mvc.perform(get("/a"))
                .andExpect(unauthenticated());

        mvc.perform(post("/a"))
                .andExpect(unauthenticated());

        mvc.perform(get("/a/b"))
                .andExpect(unauthenticated());

        mvc.perform(get("/a/b/c"))
                .andExpect(unauthenticated());
    }

    @Test
    @DisplayName("Any authenticated user can call GET /a")
    @WithUserDetails("john")
    public void testAnyAuthenticatedUserCanCallGETA() throws Exception {
        mvc.perform(get("/a"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Any user can call POST /a")
    public void testAnyUserCanCallPOSTA() throws Exception {
        mvc.perform(post("/a"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Endpoints /a/b and /a/b/c are denied")
    public void testOtherEndpointsAreUnauthorized() throws Exception {
        mvc.perform(post("/a/b"))
                .andExpect(status().isUnauthorized());

        mvc.perform(post("/a/b/c"))
                .andExpect(status().isUnauthorized());
    }
}