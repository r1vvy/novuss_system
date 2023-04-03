package com.novuss.authservice.core.security.config;

import com.novuss.authservice.core.security.JwtAuthenticationFilterService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.AntPathMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthenticationFilterService JwtAuthenticationFilterService;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeHttpRequests(authorize ->
                        authorize
                                .requestMatchers(request -> {
                                    AntPathMatcher pathMatcher = new AntPathMatcher();
                                    return pathMatcher.match("/api/v1/users/**", request.getServletPath());
                                }).permitAll()
                                .requestMatchers(request -> {
                                    AntPathMatcher pathMatcher = new AntPathMatcher();
                                    return pathMatcher.match("/api/v1/auth/authenticate", request.getServletPath());
                                }).permitAll()
                                .anyRequest().authenticated()
                )
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(JwtAuthenticationFilterService, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
