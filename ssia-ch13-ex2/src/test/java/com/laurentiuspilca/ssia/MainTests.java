package com.laurentiuspilca.ssia;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class MainTests {

    @Autowired
    private MockMvc mvc;

    @Test
    @DisplayName("Test authorize endpoint redirects to login")
    public void testAuthorizeEndpoint() throws Exception {
        mvc.perform(get("/oauth/authorize")
                .queryParam("response_type", "code")
                .queryParam("client_id", "client")
                .queryParam("scope", "read")
        )
                .andExpect(redirectedUrl("http://localhost/login"))
                .andExpect(status().isFound());
    }

}
