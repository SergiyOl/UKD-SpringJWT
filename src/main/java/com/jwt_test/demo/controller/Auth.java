package com.jwt_test.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jwt_test.demo.entity.auth.AuthResponse;
import com.jwt_test.demo.entity.auth.LoginRequest;
import com.jwt_test.demo.entity.UserDetailsImpl;
import com.jwt_test.demo.config.jwt.JwtUtils;

import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class Auth {
    private static final Logger logger = LoggerFactory.getLogger(Auth.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest loginRequest) {
        try {
            logger.info("Received Login request" + loginRequest.getUsername());
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                            loginRequest.getPassword()));

            logger.info("Login sucsesful");
            UserDetailsImpl user = (UserDetailsImpl) authentication.getPrincipal();
            String jwtToken = jwtUtils.generateTokenFromUsername(user.getUsername());
            AuthResponse authResponse = new AuthResponse(user.getUsername(), jwtToken);
            return ResponseEntity.ok().body(authResponse);
        } catch (BadCredentialsException e) {
            logger.info("Error: Bad credentials");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}