package com.jwt_test.demo.service;

import com.jwt_test.demo.entity.User;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.stereotype.Service;

// import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> readById(long id);

    User findByEmail(String email);

    User save(User user);
}