package com.jwt_test.demo.entity.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthResponse {
    private String username;
    private String accessToken;

    public AuthResponse(String username, String jwtToken) {
        this.username = username;
        this.accessToken = jwtToken;
    }
}
