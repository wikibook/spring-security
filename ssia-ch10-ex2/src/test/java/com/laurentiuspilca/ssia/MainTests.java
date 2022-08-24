package com.laurentiuspilca.ssia;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class MainTests {

    @Autowired
    private MockMvc mvc;

    @Test
    @DisplayName("Authenticating with a wrong user")
    public void loggingInWithWrongUser() throws Exception {
        mvc.perform(formLogin())
                .andExpect(unauthenticated());
    }

    @Test
    @DisplayName("Logging in authenticating with valid user")
    @WithUserDetails("mary")
    public void loggingInWithWrongAuthority() throws Exception {
        mvc.perform(formLogin()
                .user("mary").password("12345")
        )
                .andExpect(redirectedUrl("/main"))
                .andExpect(status().isFound())
                .andExpect(authenticated());
    }

}
