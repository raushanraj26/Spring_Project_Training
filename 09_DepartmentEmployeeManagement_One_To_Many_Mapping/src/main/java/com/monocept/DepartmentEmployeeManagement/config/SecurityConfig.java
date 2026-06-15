package com.monocept.DepartmentEmployeeManagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth

                .requestMatchers(HttpMethod.GET, "/api/departments/**")
                .hasAnyRole("student", "Admin")

                .requestMatchers(HttpMethod.POST, "/api/departments/create")
                .hasRole("Admin")

                .requestMatchers(HttpMethod.PUT, "/api/departments/**")
                .hasAnyRole("student", "Admin")

                .requestMatchers(HttpMethod.DELETE, "/api/departments")
                .hasRole("Admin")

                .anyRequest()
                .authenticated()

            )
            .httpBasic(Customizer.withDefaults())
            .sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            );

        return http.build();
    }

    @Bean
    UserDetailsService detailsService(PasswordEncoder passwordEncoder) {

        UserDetails admin = User.builder()
                .username("Admin")
                .password(passwordEncoder.encode("Admin123"))
                .roles("Admin")
                .build();

        UserDetails student = User.builder()
                .username("student")
                .password(passwordEncoder.encode("Student123"))
                .roles("student")
                .build();

        return new InMemoryUserDetailsManager(admin, student);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
