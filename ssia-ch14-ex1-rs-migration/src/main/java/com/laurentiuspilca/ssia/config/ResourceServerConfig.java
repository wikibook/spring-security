package com.laurentiuspilca.ssia.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class ResourceServerConfig
        extends WebSecurityConfigurerAdapter {

    @Value("${authorization-server.introspection-uri}")
    private String introspectionUri;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and().oauth2ResourceServer(
                c -> c.opaqueToken(
                        o -> {
                            o.introspectionUri(introspectionUri);
                            o.introspectionClientCredentials("resourceserver", "resourceserversecret");
                        })
        );
    }
}
