package com.laurentiuspilca.ssia;

import com.laurentiuspilca.ssia.config.MockCustomUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MainTests {

    @Autowired
    private MockMvc mvc;

    @Test
    @MockCustomUser
    @DisplayName("Test successful call on main endpoint after OAuth 2 authentication")
    public void testSuccessfulCallOnMainEndpoint() throws Exception {
        mvc.perform(get("/"))
           .andExpect(status().isOk());
    }

}
