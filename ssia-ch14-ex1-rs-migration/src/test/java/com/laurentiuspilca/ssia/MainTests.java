package com.laurentiuspilca.ssia;

import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.UUID;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureWireMock(port = 0)
public class MainTests {

    @Autowired
    private MockMvc mvc;

    @Test
    @DisplayName("Test access_token is obtained using valid client credentials")
    public void testAccessTokenIsObtainedUsingValidClientCredentials() throws Exception {
        String token = UUID.randomUUID().toString();

        stubFor(WireMock.post(urlMatching("/oauth/check_token"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(OK.value())
                        .withBody("{\"active\":true,\"exp\":1587946446,\"user_name\":\"john\",\"authorities\":[\"read\"],\"client_id\":\"client\",\"scope\":[\"read\"]}")));

        mvc.perform(
                MockMvcRequestBuilders.get("/hello")
                    .header("Authorization", "Bearer " + token))
           .andExpect(status().isOk());

    }
}
