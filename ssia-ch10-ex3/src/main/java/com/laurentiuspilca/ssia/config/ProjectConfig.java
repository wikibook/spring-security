package com.laurentiuspilca.ssia.config;

import com.laurentiuspilca.ssia.csrf.CustomCsrfTokenRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfTokenRepository;

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public CsrfTokenRepository customTokenRepository() {
        return new CustomCsrfTokenRepository();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf(c -> {
            c.csrfTokenRepository(customTokenRepository());
            c.ignoringAntMatchers("/ciao");

//            HandlerMappingIntrospector i = new HandlerMappingIntrospector();
//            MvcRequestMatcher r = new MvcRequestMatcher(i, "/ciao");
//            c.ignoringRequestMatchers(r);

//            String pattern = ".*[0-9].*";
//            String httpMethod = HttpMethod.POST.name();
//            RegexRequestMatcher r = new RegexRequestMatcher(pattern, httpMethod);
//            c.ignoringRequestMatchers(r);
        });

        http.authorizeRequests()
             .anyRequest().permitAll();
    }
}
