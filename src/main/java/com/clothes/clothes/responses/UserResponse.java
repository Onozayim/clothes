package com.clothes.clothes.responses;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import com.clothes.clothes.entities.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    public UserResponse(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        authorities = user.getAuthorities();
    }

    private Long id;
    private String username;
    private String email;
    Collection<? extends GrantedAuthority> authorities;
}