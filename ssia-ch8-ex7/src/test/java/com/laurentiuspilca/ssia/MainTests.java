package com.laurentiuspilca.ssia;

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
    @DisplayName("Endpoint /email with a valid email address")
    public void testCallingEmailWithValidEmail() throws Exception {
        mvc.perform(get("/email/test@example.com"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Endpoint /email with an invalid email address")
    public void testCallingEmailWithInvalidEmail() throws Exception {
        mvc.perform(get("/email/test@example.ro"))
                .andExpect(status().isUnauthorized());
    }
}
