package com.codingshuttle.youtube.hospitalManagement.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

// Marks this class as a JPA Entity -> It will be mapped to a database table
@Entity

// Lombok annotations -> They auto-generate getters, setters, constructors, builder, etc.
@Getter          // generates getter methods for all fields
@Setter          // generates setter methods for all fields
@AllArgsConstructor // generates constructor with all fields
@NoArgsConstructor  // generates default (no-arg) constructor
@Builder            // allows you to use Builder pattern for object creation

// This specifies the database table name ("app_user")
@Table(name = "app_user")

// The User entity implements Spring Security's UserDetails interface
// -> This makes it compatible with Spring Security Authentication
public class User implements UserDetails {

    // Primary Key (id) -> auto-incremented in database
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ❌ PROBLEM: @JoinColumn is WRONG here because it's used for relationships
    // ✅ Correct is @Column (this is just a normal field, not a foreign key)
    @JoinColumn(unique = true, nullable = false)
    private String username;   // unique username (login credential)

    private String password;   // user password (will be stored as encrypted hash)

    // This method defines what authorities (roles/permissions) this user has
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Returning empty list means user has NO roles -> every restricted request will fail (403)
        return List.of();
    }

    // UserDetails also requires other methods like getPassword(), getUsername(),
    // isAccountNonExpired(), isAccountNonLocked(), isCredentialsNonExpired(), isEnabled()
    // If not overridden, Spring Security will not work correctly.
}
