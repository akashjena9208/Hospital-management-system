package com.codingshuttle.youtube.hospitalManagement.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class AppConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    /**
     * Defines a PasswordEncoder bean that uses BCrypt hashing.

     * BCrypt is a strong one-way hashing algorithm that adds a random salt
     * to each password and performs multiple hashing rounds, making it
     * resistant to brute-force and rainbow table attacks.

     * Spring Security will use this bean whenever passwords need to be
     * encoded (e.g., when creating users) or matched (e.g., during login).
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Uses BCrypt for secure password hashing
    }



    /**
     * Creates an in-memory user store for authentication.
     * This is mainly used for testing or small applications without a database.
     */
    /**
     * Configures an in-memory user details service.
     *
     * ✅ This is useful for:
     *    - Small applications
     *    - Prototypes or demos
     *    - Testing authentication without a database
     */
 //   @Bean       // in memory UserDetailsService
    UserDetailsService userDetailsService() {
        // Creating an Admin user with encoded password
        UserDetails user1 = User.withUsername("admin")
                .password(passwordEncoder().encode("akash")) // Password is encoded for security
                .roles("ADMIN")
                .build();

        // Creating a Patient user with encoded password
        UserDetails user2 = User.withUsername("patient")
                .password(passwordEncoder().encode("akash"))
                .roles("PATIENT")
                .build();

        // In-memory user storage with both users
        return new InMemoryUserDetailsManager(user1, user2);
    }

}
