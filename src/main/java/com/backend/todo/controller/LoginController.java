package com.backend.todo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.todo.model.ErrorResponse;
import com.backend.todo.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import com.backend.todo.service.LoginValidationService;
import com.backend.todo.service.LoginService;
@RestController
@RequestMapping("/api/v1/login")
public class LoginController {


    private final LoginValidationService loginValidationService;
    private final LoginService loginService;

    public LoginController(LoginValidationService loginValidationService, LoginService loginService) {
        this.loginValidationService = loginValidationService;
        this.loginService = loginService;
    }

    @PostMapping
    public ResponseEntity<?> login(@RequestBody User user) {
        // Realizar validaciones de inicio de sesión
        ErrorResponse validationError = loginValidationService.validateLogin(user);
        if (validationError != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationError);
        }

        return loginService.login(user);
    }
}

