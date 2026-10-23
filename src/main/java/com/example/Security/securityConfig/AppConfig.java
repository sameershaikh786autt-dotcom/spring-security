package com.example.Security.securityConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
                        .requestMatchers("/api/access/**").permitAll()
                        .requestMatchers("/api/login/**").hasAnyRole("USER")
                        .anyRequest().authenticated()
                );


        // // HTTP Basic Authentication enable kiya:
        http.httpBasic(Customizer.withDefaults());

        return http.build();
    }


    // FOR PASSWORD ENCRYPTION
//    @Bean
//    public BCryptPasswordEncoder bCryptPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
}
