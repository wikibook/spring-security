package com.laurentiuspilca.ssia.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

@Configuration
public class ResourceServerConfig extends WebSecurityConfigurerAdapter {

    @Value("${jwt.key}")
    private String jwtKey;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated()
            .and()
                .oauth2ResourceServer(c -> c.jwt( jwt -> {
                    jwt.decoder(jwtDecoder());
                }));
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        byte [] key = jwtKey.getBytes();
        SecretKey originalKey = new SecretKeySpec(key, 0, key.length, "AES");

        NimbusJwtDecoder jwtDecoder =
                NimbusJwtDecoder.withSecretKey(originalKey)
                .build();

        return jwtDecoder;
    }
}
