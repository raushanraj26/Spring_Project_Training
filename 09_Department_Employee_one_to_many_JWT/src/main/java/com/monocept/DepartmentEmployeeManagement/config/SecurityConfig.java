package com.monocept.DepartmentEmployeeManagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.monocept.DepartmentEmployeeManagement.security.JwtAuthenticationFilter;

@Configuration
public class SecurityConfig {

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http,AuthenticationProvider authenticationProvider,
            JwtAuthenticationFilter jwtAuthenticationFilter ) throws Exception {

		http.csrf(csrf -> csrf.disable()).authenticationProvider(authenticationProvider).authorizeHttpRequests(auth -> auth
				.requestMatchers(HttpMethod.POST,"/api/auth/login").permitAll()
				.requestMatchers(HttpMethod.GET, "/api/departments/**").hasAnyRole("USER", "ADMIN")
				.requestMatchers(HttpMethod.POST, "/api/departments/create").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/departments/**")
                .hasAnyRole("USER", "ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/departments")
                .hasRole("ADMIN")
				.anyRequest().authenticated()
		).httpBasic(Customizer.withDefaults())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

//	@Bean
//	UserDetailsService detailsService(PasswordEncoder passwordEncoder) {
//
//		UserDetails admin = User.builder().username("Admin").password(passwordEncoder.encode("Admin123")).roles("Admin")
//				.build();
//
//		UserDetails student = User.builder().username("student").password(passwordEncoder.encode("Student123"))
//				.roles("student").build();
//
//		return new InMemoryUserDetailsManager(admin, student);
//	}
	
	@Bean
    AuthenticationProvider authenticationProvider(
            UserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder) {

        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider(userDetailsService);

//        authenticationProvider.setUserDetailsService();
        authenticationProvider.setPasswordEncoder(passwordEncoder);

        return authenticationProvider;
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
