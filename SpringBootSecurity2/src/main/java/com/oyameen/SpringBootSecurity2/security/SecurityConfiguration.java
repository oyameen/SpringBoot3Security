package com.oyameen.SpringBootSecurity2.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration{
    @Autowired
    private UserPrincipalDetailsService userPrincipalDetailsService;

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return //
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize ->{
                    authorize.requestMatchers( "/","/index").permitAll();
                    authorize.requestMatchers("/profile/**").authenticated();
                    authorize.requestMatchers("/admin/**", "/h2-console/**").hasRole("ADMIN");
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
    AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userPrincipalDetailsService);
        return daoAuthenticationProvider;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
