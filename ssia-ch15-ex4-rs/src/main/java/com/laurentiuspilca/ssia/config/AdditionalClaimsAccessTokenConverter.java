package com.laurentiuspilca.ssia.config;

import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.Map;

public class AdditionalClaimsAccessTokenConverter
        extends JwtAccessTokenConverter {

    @Override
    public OAuth2Authentication extractAuthentication(Map<String, ?> map) {
        var authentication = super.extractAuthentication(map);
        authentication.setDetails(map);
        return authentication;
    }
}
