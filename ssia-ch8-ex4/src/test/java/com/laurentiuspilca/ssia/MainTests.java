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
    @DisplayName("Endpoint /product can be called if it has a valid path variable value")
    public void testCallingProductWithValidParam() throws Exception {
        mvc.perform(get("/product/12345"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Endpoint /product cannot be called if it has a non-valid path variable value")
    public void testCallingProductWithInvalidParam() throws Exception {
        mvc.perform(get("/product/abc12"))
                .andExpect(status().isUnauthorized());
    }
}
