package com.backend.todo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.logging.Logger;

import com.backend.todo.model.ErrorResponse;
import com.backend.todo.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.backend.todo.service.LoginServices.LoginService;
import com.backend.todo.service.LoginServices.LoginValidationService;
@RestController
@RequestMapping("/api/v1/login")
public class LoginController {
    private final LoginValidationService loginValidationService;
    private final LoginService loginService;
    private final Logger logger = Logger.getLogger(LoginController.class.getName()); // Nombre de la clase como identificador del logger

    public LoginController(LoginValidationService loginValidationService, LoginService loginService) {
        this.loginValidationService = loginValidationService;
        this.loginService = loginService;
    }

    @PostMapping
    public ResponseEntity<?> login(@RequestBody User user) {
        logger.info("POST request received for login");

        // Realizar validaciones de inicio de sesión
        ErrorResponse validationError = loginValidationService.validateLogin(user);
        if (validationError != null) {
            logger.warning("Login validation failed");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationError);
        }

        logger.info("Login validation successful");
        return loginService.login(user);
    }
}


