package com.beebrick.config;

import com.beebrick.service.impl.CustomerSecurityService;
import com.beebrick.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
@Order(3)
public class config extends WebSecurityConfigurerAdapter{

    @Autowired
    private Environment env;

    @Autowired
    private CustomerSecurityService customerSecurityService;

    private BCryptPasswordEncoder passwordEncoder() {
        return SecurityUtil.passwordEncoderBCry();
    }

    private static final String[] PUBLIC_MATCHERS = {
            "/assets/css/**",
            "/assets/js/**",
            "/assets/img/**",
            "/assets/fonts/**",

            "/ckeditor/**",
            "/images/**",

            "/shop/**",
            /*"/product-detail",*/
            "/about-us",
            "/contact",
            "/login",
            "/",
            "/error",

            "/web-newUser",
            "/updateUserInfo",
            "/web-forgetPassword",
            "/newCustomer",
            "/getimage/**",

            "/admin/**"
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests().
	antMatchers(PUBLIC_MATCHERS).permitAll()
                .anyRequest().authenticated();

        http
                .csrf().disable().cors().disable()
                .formLogin().failureUrl("/login?error")
                /*.defaultSuccessUrl("/")*/
                .loginPage("/login").permitAll()
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/?logout").deleteCookies("remember-me").permitAll()
                .and()
                .rememberMe();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customerSecurityService).passwordEncoder(passwordEncoder());
    }




}
