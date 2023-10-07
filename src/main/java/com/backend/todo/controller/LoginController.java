package com.backend.todo.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import com.backend.todo.model.User;
import com.backend.todo.service.LoginServices.LoginService;

import java.util.logging.Logger;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/login")
public class LoginController {

    private final LoginService loginService;
    private final Logger logger = Logger.getLogger(LoginController.class.getName());

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping
    public ResponseEntity<?> login(@RequestBody final User user) {
        logger.info("POST request received for login for user: " + user.getUsername());

        // Login validations
        ResponseEntity<?> response = loginService.login(user);
        
        if (!response.getStatusCode().is2xxSuccessful()) {
            logger.warning("Login failed for user: " + user.getUsername());
        } else {
            logger.info("Login successful for user: " + user.getUsername());
        }

        return response;
    }
}
