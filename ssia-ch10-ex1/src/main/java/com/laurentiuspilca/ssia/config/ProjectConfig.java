package com.laurentiuspilca.ssia.config;

import com.laurentiuspilca.ssia.filters.CsrfTokenLogger;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterAfter(
                new CsrfTokenLogger(),
                CsrfFilter.class)
                .authorizeRequests()
                    .anyRequest().permitAll();
    }
}
