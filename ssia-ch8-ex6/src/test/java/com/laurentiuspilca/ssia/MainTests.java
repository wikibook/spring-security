package com.laurentiuspilca.ssia;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MainTests {

    @Autowired
    private MockMvc mvc;

    @Test
    @DisplayName("Endpoint /video without authentication")
    public void testCallingVideoWithoutAuthentication() throws Exception {
        mvc.perform(get("/video/ca/fr"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("Endpoint /video with authentication as ca/fr without premium")
    @WithUserDetails("john")
    public void testCallingVideoWithAuthenticationWithoutPremium() throws Exception {
        mvc.perform(get("/video/ca/fr"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Endpoint /video with authentication as ro/ro without premium")
    @WithUserDetails("john")
    public void testCallingVideoWithAuthenticationWithoutPremiumForRO() throws Exception {
        mvc.perform(get("/video/ro/ro"))
                .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("Endpoint /video with authentication as ro/ro with premium")
    @WithUserDetails("jane")
    public void testCallingVideoWithAuthenticationWithPremiumforRO() throws Exception {
        mvc.perform(get("/video/ro/ro"))
                .andExpect(status().isOk());
    }
}
