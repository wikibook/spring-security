package com.laurentiuspilca.ssia;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EndpointCiaoTests {

    @Autowired
    private MockMvc mvc;

    @Test
    @DisplayName("The /ciao endpoint cannot be called unauthenticated")
    public void testFailedAuthentication() throws Exception {
        mvc.perform(get("/ciao"))
                .andExpect(unauthenticated());
    }

    @Test
    @DisplayName("A user without privileges can authenticate but is not authorized to call /ciao")
    @WithUserDetails("john")
    public void testSuccessfulAuthentication() throws Exception {
        mvc.perform(get("/ciao"))
                .andExpect(authenticated())
                .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("A user with privileges can authenticate and is authorized to call /ciao")
    @WithUserDetails("jane")
    public void testSuccessfulAuthorization() throws Exception {
        mvc.perform(get("/ciao"))
                .andExpect(authenticated())
                .andExpect(status().isOk());
    }
}
