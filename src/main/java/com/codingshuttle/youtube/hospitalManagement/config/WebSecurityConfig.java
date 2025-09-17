package com.codingshuttle.youtube.hospitalManagement.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * WebSecurityConfig is the Spring Security configuration class.
 * It defines authentication and authorization rules for the application.
 */
@Configuration // Marks this class as a Spring configuration class
@RequiredArgsConstructor // Generates a constructor for all final fields (dependency injection)
public class WebSecurityConfig {

    // Injected PasswordEncoder bean used to securely encode passwords
   // private final PasswordEncoder passwordEncoder;

    /**
     * Configures security rules for different URL patterns.
     * Defines which roles have access to which endpoints.
     * * Defines the security rules for HTTP requests.
     * * Sets up which URLs require authentication and which roles can access them.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests(auth -> auth
                        // Allows public endpoints without authentication
                        .requestMatchers("/public/**").permitAll()

                        // Only users with the ADMIN role can access /admin/**
                        .requestMatchers("/admin/**").hasRole("ADMIN")

                        // Both DOCTOR and ADMIN roles can access /doctors/**
                        .requestMatchers("/doctors/**").hasAnyRole("DOCTOR", "ADMIN")
                )
                // Enables default form-based login
                .formLogin(Customizer.withDefaults());

        // Builds and returns the configured security filter chain
        return httpSecurity.build();
    }

}
/*
=====================================
🏥 HOSPITAL MANAGEMENT SYSTEM (HMS) API
Base URL: http://localhost:8080/api/v1
=====================================


1️⃣ PUBLIC ENDPOINTS (No Authentication Required)
-------------------------------------------------
-> Anyone can access without login

GET    /public/doctors
Full:  http://localhost:8080/api/v1/public/doctors
Desc:  Get the list of all doctors


2️⃣ PATIENT ENDPOINTS (Requires Authentication → Role: PATIENT or ADMIN)
------------------------------------------------------------------------
-> Only logged-in PATIENT or ADMIN can access

POST   /patients/appointments
Full:  http://localhost:8080/api/v1/patients/appointments
Desc:  Create a new appointment (booking)

GET    /patients/profile
Full:  http://localhost:8080/api/v1/patients/profile
Desc:  Get the logged-in patient's profile


3️⃣ DOCTOR ENDPOINTS (Requires Authentication → Role: DOCTOR or ADMIN)
-----------------------------------------------------------------------
-> Only logged-in DOCTOR or ADMIN can access

GET    /doctors/appointments
Full:  http://localhost:8080/api/v1/doctors/appointments
Desc:  Get all appointments of the logged-in doctor


4️⃣ ADMIN ENDPOINTS (Requires Authentication → Role: ADMIN only)
-----------------------------------------------------------------
-> Only logged-in ADMIN can access

GET    /admin/patients?page={page}&size={size}
Full:  http://localhost:8080/api/v1/admin/patients?page=0&size=10
Desc:  Get paginated list of all patients


5️⃣ SECURITY & LOGIN (Spring Security Default)
----------------------------------------------
-> Spring Security handles login for all users (admin/patient/doctor)

GET/POST /login
Full:  http://localhost:8080/api/v1/login
Desc:  Login endpoint (default form-based login provided by Spring Security)


=====================================
🔐 ROLE BASED ACCESS SUMMARY
=====================================
- /public/**      → Accessible by Everyone (No login required)
- /patients/**    → Accessible by PATIENT and ADMIN
- /doctors/**     → Accessible by DOCTOR and ADMIN
- /admin/**       → Accessible by ADMIN only

*/

/*
===============================
 HOSPITAL MANAGEMENT API ENDPOINTS
 Base URL: http://localhost:8080/api/v1
===============================

1️⃣ Public Endpoints (No Authentication Required)
-------------------------------------------------
GET    /public/doctors
Full URL: http://localhost:8080/api/v1/public/doctors

2️⃣ Patient Endpoints (Requires Authentication — Role: PATIENT or ADMIN)
------------------------------------------------------------------------
POST   /patients/appointments
Full URL: http://localhost:8080/api/v1/patients/appointments

GET    /patients/profile
Full URL: http://localhost:8080/api/v1/patients/profile

3️⃣ Security & Login
--------------------
GET/POST /login (Spring Security default form login)
Full URL: http://localhost:8080/api/v1/login

4️⃣ Role-Based Access Summary
------------------------------
- /public/**      → Accessible by Everyone
- /admin/**       → Role: ADMIN only
- /doctors/**     → Role: DOCTOR or ADMIN
- /patients/**    → Role: PATIENT or ADMIN
*/
