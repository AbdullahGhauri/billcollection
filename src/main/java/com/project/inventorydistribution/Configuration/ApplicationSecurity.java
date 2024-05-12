package com.project.inventorydistribution.Configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class ApplicationSecurity {

    @Autowired
    private JwtRequestFilter jwtRequestFilter;
    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        System.out.println("applicationSecurity");
         http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(a-> a.
                        requestMatchers("/agentAuth/**").permitAll().
                        requestMatchers("/swagger-path/**").permitAll().
                        requestMatchers("/v3/api-docs/**").permitAll().
                        requestMatchers("/swagger-ui/**").permitAll()
                        .anyRequest().authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                        .authenticationProvider(authenticationProvider)
                                .addFilterBefore(jwtRequestFilter,UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}