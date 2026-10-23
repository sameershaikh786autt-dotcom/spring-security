package com.example.Security.securityConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class AppConfig {


    // FOR SECURITYCHAINFILTERS
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Disabled csrf
//        http.csrf(csrf -> csrf.disable());

        // DISABLE CORS
        http.cors(cors -> cors.disable());


        // // Authorization rules
        http.authorizeHttpRequests
                (auth -> auth
//                        .requestMatchers("/api/access/**", "/api/login/**").permitAll()
//                        .requestMatchers("/api/admin/**").hasAnyRole("ADMIN")
                        .anyRequest().authenticated()
                );


       // HTTP Basic Authentication enable kiya:
        http.httpBasic(Customizer.withDefaults());

        return http.build();
    }


    // 2. In-Memory Users Setup (Bina Database ke RAM mein users store honge)
    @Bean
    public UserDetailsService userDetailsService(BCryptPasswordEncoder bCryptPasswordEncoder) throws Exception {


        // UserDetails for users
        UserDetails user = User.builder()
                .username("user")
                .password(bCryptPasswordEncoder.encode("user123"))
                .roles("USER")
                .build();


        UserDetails admin = User.builder()
                .username("admin")
                .password(bCryptPasswordEncoder.encode("admin123"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, admin);

    }


    // FOR PASSWORD ENCRYPTION
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
