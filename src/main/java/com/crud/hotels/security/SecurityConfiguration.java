package com.crud.hotels.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.stream.Collectors;
import java.util.stream.Stream;


@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private static final String LOGIN_PROCESSING_URL = "/login";
    private static final String LOGIN_FAILURE_URL = "/login?error";
    private static final String LOGIN_URL = "/login";
    private static final String SUCCESS_URL = "/rooms";
    private static final String LOGOUT_SUCCESS_URL = "/login";

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .requestCache().requestCache(new CustomRequestCache())
                .and().authorizeRequests()
                .requestMatchers(SecurityUtils::isFrameworkInternalRequest).permitAll()

                .anyRequest().authenticated()

                .and().formLogin()
                .loginPage(LOGIN_URL).permitAll()
                .loginProcessingUrl(LOGIN_PROCESSING_URL)
                .failureUrl(LOGIN_FAILURE_URL)
                .successForwardUrl(SUCCESS_URL)
                .and().logout().logoutSuccessUrl(LOGOUT_SUCCESS_URL);
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager(Stream.of(
                "Daniel,{noop}password,USER",
                "Krzysio,{noop}password,USER",
                "Bartek,{noop}password,USER",
                "Bogdan,{noop}password,OWNER",
                "Janusz,{noop}password,OWNER",
                "Zbycho,{noop}password,ADMIN").map(
                user -> {
                    String[] split = user.split(",");
                    return User.withUsername(split[0]).password(split[1]).roles(split[2]).build();
                }).collect(Collectors.toList()));
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers(
                "/VAADIN/**",
                "/favicon.ico",
                "/robots.txt",
                "/manifest.webmanifest",
                "/sw.js",
                "/offline.html",
                "/icons/**",
                "/images/**",
                "/styles/**",
                "/h2-console/**");
    }
}
