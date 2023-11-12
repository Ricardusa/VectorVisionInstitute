package com.capstone.vectorvisioninstitute.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((auth) -> auth
                        /* PUBLIC */
                        .requestMatchers(
                                "/public/**",
                                //"", these are treated similarly "/"
                                "/",
                                "/home",
                                "/holidays/**",
                                "/contact",
                                "/saveMsg",
                                "/assets/**",
                                "/courses",
                                "/about",
                                "/logout",
                                "/login")
                        .permitAll()

                        /* LOGGED IN */
                        .requestMatchers(
                                "/dashboard",
                                "/displayProfile",
                                "/updateProfile")
                        .authenticated()

                        /* IS STUDENT */
                        .requestMatchers(
                                "/student/**")
                        .hasRole("STUDENT")

                        /* IS ADMIN */
                        .requestMatchers(
                                "/displayMessages/**", //changed -> /**
                                "/admin/**",
                                "/closeMsg/**")
                        .hasRole("ADMIN"))
                .formLogin((login) -> login
                        .loginPage("/login")
                        .defaultSuccessUrl("/dashboard")
                        .failureUrl("/login?error=true")
                        .permitAll())
                .logout((logout) -> logout
                        .logoutSuccessUrl("/login?logout=true")
                        .invalidateHttpSession(true)
                        .permitAll())
                .csrf((csrf) -> csrf
                        .ignoringRequestMatchers(
                                "/public/**",
                                "/saveMsg"))
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


}
