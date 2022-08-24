package com.laurentiuspilca.ssia;

import com.laurentiuspilca.ssia.authentication.proxy.AuthenticationServerProxy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MainTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MockBean restTemplate;

    @MockBean
    private AuthenticationServerProxy authenticationServerProxy;

    @Test
    @DisplayName("Test /login with username and password")
    public void testLoginWithUsernameAndPassword() throws Exception {
        mvc.perform(get("/login").servletPath("/login")
                .header("username", "bill")
                .header("password", "12345")
        )
        .andExpect(status().isOk());

        verify(authenticationServerProxy)
                .sendAuth("bill", "12345");
    }

    @Test
    @DisplayName("Test /login with username and otp")
    public void testLoginWithUsernameAndOtp() throws Exception {
        when(authenticationServerProxy.sendOTP("bill", "5555"))
                .thenReturn(true);

        mvc.perform(get("/login").servletPath("/login")
                .header("username", "bill")
                .header("code", "5555")
        )
        .andExpect(header().exists("Authorization"))
        .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Test /test with Authorization header")
    public void testRequestWithAuthorizationHeader() throws Exception {
        when(authenticationServerProxy.sendOTP("bill", "5555"))
                .thenReturn(true);

        var authorizationHeaderValue =
            mvc.perform(get("/login").servletPath("/login")
                    .header("username", "bill")
                    .header("code", "5555")
            )
            .andReturn().getResponse().getHeader("Authorization");

        mvc.perform(get("/test")
                .header("Authorization", authorizationHeaderValue))
                .andExpect(status().isOk());
    }
}
