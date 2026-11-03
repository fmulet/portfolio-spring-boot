package com.portfolio.my_porfolio_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.portfolio.my_porfolio_backend.service.IUserDetailsService;

import lombok.RequiredArgsConstructor;

/**
 * WebSecurityConfig configurado de forma limpia para REST
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final IUserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/login").permitAll()
                        .requestMatchers("/test-education", "/test-experience", "/test-project", "/test-skill",
                                "/test-personal-info")
                        .authenticated()
                        .anyRequest().permitAll());

        return http.build();
    }

    // Solución definitiva para Spring Boot 4 / Spring Security 7
    @Bean
    public AuthenticationManager authenticationManager(PasswordEncoder passwordEncoder) {
        // En las nuevas versiones se pasa el UserDetailsService obligatoriamente en el
        // constructor
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(
                (UserDetailsService) userDetailsService);

        authProvider.setPasswordEncoder(passwordEncoder);

        return new ProviderManager(authProvider);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
