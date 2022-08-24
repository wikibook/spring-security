package com.laurentiuspilca.ssia.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class ResourceServerConfig
        extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and().oauth2ResourceServer(
                c -> c.opaqueToken(
                        o -> {
                            o.introspectionUri("http://localhost:8080/oauth/check_token");
                            o.introspectionClientCredentials("resourceserver", "resourceserversecret");
                        })
        );
    }
}
