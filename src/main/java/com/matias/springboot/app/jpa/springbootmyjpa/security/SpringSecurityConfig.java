package com.matias.springboot.app.jpa.springbootmyjpa.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.matias.springboot.app.jpa.springbootmyjpa.security.filter.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    @Autowired
    private AuthenticationConfiguration configuration;

    @Bean
    AuthenticationManager authenticationManager() throws Exception{
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder PasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @SuppressWarnings("deprecation")
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        return http.authorizeRequests((authorize) -> authorize
                .requestMatchers(HttpMethod.GET, "/app/users/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/app/users/register").permitAll()
                .requestMatchers(HttpMethod.PUT, "/app/users/update/{id}").hasAnyRole("ROLE_ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/app/users/delete/{id}").hasAnyRole("ROLE_ADMIN")
                .anyRequest().denyAll()
            )
            .addFilter(new JwtAuthenticationFilter(authenticationManager()))
            .csrf(config -> config.disable())
            .sessionManagement(managment -> managment.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .build();
    }
}
