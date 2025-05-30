package com.wora.qatrat7ayat.security.config;

import com.wora.qatrat7ayat.security.exception.CustomAccessDeniedHandler;
import com.wora.qatrat7ayat.security.exception.CustomAuthenticationEntryPoint;
import com.wora.qatrat7ayat.security.filters.JwtRequestFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtRequestFilter jwtRequestFilter;
    private static final String ADMIN =  "ADMIN";
    private static final String COORDINATOR =  "COORDINATOR";

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(request -> {
                    var corsConfig = new org.springframework.web.cors.CorsConfiguration();
                    corsConfig.setAllowCredentials(true);
                    corsConfig.addAllowedOrigin("http://localhost:5173");
                    corsConfig.addAllowedHeader("*");
                    corsConfig.addAllowedMethod("*");
                    corsConfig.addExposedHeader("*");
                    return corsConfig;
                }))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/blood-requests").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/cities").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/donors").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/donors").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/blood-requests").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/hospitals").permitAll()
                        .requestMatchers("/api/v1/public/articles").permitAll()
                        .requestMatchers("/api/v1/public/articles/latest").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/articles/{id}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/articles").hasRole(ADMIN)
                        .requestMatchers(HttpMethod.PUT, "/api/v1/articles/status/{id}").hasRole(ADMIN)
                        .requestMatchers("/api/v1/articles").hasRole(COORDINATOR)
                        .requestMatchers("/api/v1/articles/**").hasRole(COORDINATOR)
                        .requestMatchers(HttpMethod.GET, "/api/v1/my-articles").hasRole(COORDINATOR)
                        .requestMatchers("/api/v1/admin/**").hasRole(ADMIN)
                        .requestMatchers(HttpMethod.GET, "/api/v1/admin/accounts").hasRole(ADMIN)
                        .requestMatchers(HttpMethod.GET, "/api/v1/admin/accounts").hasRole(ADMIN)
                        .requestMatchers(HttpMethod.POST, "/api/v1/chat").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/users").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/messages/{receiverId}").permitAll()
                        .requestMatchers("/ws/**").permitAll()
                        .requestMatchers("/api/**").authenticated()
                        .anyRequest().authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(exception -> exception
                        .accessDeniedHandler(customAccessDeniedHandler())
                        .authenticationEntryPoint(customAuthenticationEntryPoint())
                );

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    private AuthenticationEntryPoint customAuthenticationEntryPoint() {
        return new CustomAuthenticationEntryPoint();
    }

    public CustomAccessDeniedHandler customAccessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
