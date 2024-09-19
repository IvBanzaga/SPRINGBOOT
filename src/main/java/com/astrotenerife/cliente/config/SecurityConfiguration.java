package com.astrotenerife.cliente.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity

public class SecurityConfiguration {

    @Autowired
    private CustomAccessFilter customAccessFilter;

    @Bean
    public SecurityFilterChain filterChain (HttpSecurity http ) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);

        /*http.authorizeHttpRequests(request -> {
            // Acceder a la página de registro y login sin autenticación
            request.requestMatchers("/api/register","/api/login").permitAll();
            // Acceder a la API con autenticación
            request.requestMatchers("/api/**").authenticated();
        });*/

        http.addFilterBefore(customAccessFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
