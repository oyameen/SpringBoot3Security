package com.oyameen.SpringBootSecurity1.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return //
                http.csrf(AbstractHttpConfigurer::disable)
                        .authorizeHttpRequests(authorize -> {
                            authorize.requestMatchers("/", "/index").permitAll();
                            authorize.requestMatchers("/profile/**").authenticated();
                            authorize.requestMatchers("/admin/**").hasRole("ADMIN");
                            authorize.requestMatchers("/management/**").hasAnyRole("ADMIN", "MANAGER");
                            authorize.requestMatchers("/api/public/test1").hasAuthority("ACCESS_TEST1");
                            authorize.requestMatchers("/api/public/test2").hasAuthority("ACCESS_TEST2");
                            authorize.requestMatchers("/api/public/users").hasRole("ADMIN");
                            authorize.anyRequest().authenticated();
                        })
                        .httpBasic(Customizer.withDefaults())
                        .headers(httpSecurityHeadersConfigurer -> httpSecurityHeadersConfigurer.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                        .build();

    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {

        UserDetails user = User.builder()
                .username("user@xyz.com")
                .password(passwordEncoder().encode("user123"))
                .authorities("ROLE_USER")
                .build();

        UserDetails admin = User.builder()
                .username("admin@xyz.com")
                .password(passwordEncoder().encode("admin123"))
                .authorities("ROLE_ADMIN", "ACCESS_TEST1", "ACCESS_TEST2")
                .build();
        UserDetails manager = User.builder()
                .username("manager@xyz.com")
                .password(passwordEncoder().encode("manager123"))
                .authorities("ROLE_MANAGER","ACCESS_TEST1")
                .build();

        return new InMemoryUserDetailsManager(user, admin, manager);
    }
}
