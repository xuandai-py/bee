package com.beebrick.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@Order(3)
public class BaseSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String[] CLASS_PATH = {
            "/assets/**",
            "/images/**",
            "/web/**"
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers(CLASS_PATH)
                .permitAll().anyRequest().authenticated()
                .and().exceptionHandling().accessDeniedPage("/web/403");

    }


}
