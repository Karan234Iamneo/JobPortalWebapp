package com.example.demo.config;

import com.example.demo.security.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtFilter jwtAuthFilter;

    public SecurityConfig(JwtFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll()

                        // Jobseeker
                        .requestMatchers("/api/jobseekers/**").hasRole("jobseeker")
                        .requestMatchers("/api/applications/**").hasRole("jobseeker")
                        .requestMatchers("/api/application-answers/**").hasRole("jobseeker")

                        // Recruiter
                        .requestMatchers("/api/companies/**").hasRole("recruiter")
                        // Public/Jobseeker can view published jobs
                        .requestMatchers(HttpMethod.GET, "/api/jobs", "/api/jobs/status/published", "/api/jobs/{id}")
                        .permitAll()

                        // Recruiter can manage all jobs (including PUT/POST)
                        .requestMatchers("/api/jobs/**").hasRole("recruiter")

                        .requestMatchers("/api/job-questions/**").hasRole("recruiter")
                        .requestMatchers("/api/recruiter/**").hasRole("recruiter")

                        // Admin
                        .requestMatchers("/api/bans/**").hasRole("admin")
                        .requestMatchers("/api/admin/**").hasRole("admin")
                        .requestMatchers("/api/users/**").hasRole("admin")

                        .anyRequest().denyAll())
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}
