package com.jwt_test.demo.service.auth;

import com.jwt_test.demo.entity.User;
import com.jwt_test.demo.entity.UserDetailsImpl;
import com.jwt_test.demo.repository.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("Loading user: " + username);
        User user = userRepository.findByUsername(username);
        if (user == null) {
            logger.info("User not found");
            throw new UsernameNotFoundException("User Not Found with username: " + username);
        }
        logger.info("User found: " + user.getUsername());
        return UserDetailsImpl.build(user);
    }
}