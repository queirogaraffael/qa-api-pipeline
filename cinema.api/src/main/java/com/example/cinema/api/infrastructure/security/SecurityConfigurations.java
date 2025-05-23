package com.example.cinema.api.infrastructure.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@Scope("singleton") // apesar de ser um singleton por padrão, coloquei só para deixar explícito
public class SecurityConfigurations {

    private final SecurityFilter securityFilter;

    public SecurityConfigurations(SecurityFilter securityFilter) {
        this.securityFilter = securityFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/login", "/users").permitAll()

                        // Endpoints públicos do GenreResource
                        .requestMatchers(HttpMethod.GET, "/api/genres").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/genres/{id}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/genres/search").permitAll()

                        // Endpoints públicos do MovieResource
                        .requestMatchers(HttpMethod.GET, "/api/movies").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/movies/{id}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/movies/search").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/movies/genre/{genreId}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/movies/search/genre/{genreId}").permitAll()

                        // Swagger (também público)
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-resources/**", "/webjars/**").permitAll()

                        // O restante exige autenticação
                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
