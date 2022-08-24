package com.laurentiuspilca.ssia;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class MainTests {

    @Autowired
    private MockMvc mvc;

    @Test
    @DisplayName("Endpoint /hello with authentication")
    @WithUserDetails("john")
    public void testCallingHelloWithAuthentication() throws Exception {
        mvc.perform(get("/hello"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Endpoint /hello/ with authentication")
    @WithUserDetails("john")
    public void testCallingHelloVariationWithAuthentication() throws Exception {
        mvc.perform(get("/hello/"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Endpoint /hello without authentication")
    public void testCallingHelloWithoutAuthentication() throws Exception {
        mvc.perform(get("/hello"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("Endpoint /hello/ without authentication")
    public void testCallingHelloVariationWithoutAuthentication() throws Exception {
        mvc.perform(get("/hello/"))
                .andExpect(status().isUnauthorized());
    }
}
