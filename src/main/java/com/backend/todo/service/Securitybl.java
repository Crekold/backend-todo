package com.backend.todo.service;

import org.springframework.stereotype.Service;

import com.backend.todo.repository.UserRepository;

@Service
public class Securitybl {
    private final UserRepository userRepository;
    public Securitybl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean validateUser(String username, String password) {
        if (username == null || username.isEmpty()) {
            return false;
        }
        if (password == null || password.isEmpty()) {
            return false;
        }
        return true;
    }
}
