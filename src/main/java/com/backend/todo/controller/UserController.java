package com.backend.todo.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.todo.service.UserServices.UserService;
import com.backend.todo.service.UserServices.UserValidationService;
import com.backend.todo.model.ErrorResponse;
import com.backend.todo.model.User;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
@CrossOrigin(origins = "*, maxAge = 3600")
@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserValidationService userValidationService;
    private final UserService userService;
    private final Logger logger = Logger.getLogger(UserController.class.getName()); // Nombre de la clase como identificador del logger

    public UserController(UserValidationService userValidationService, UserService userService) {
        this.userValidationService = userValidationService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        logger.info("GET request received for getAllUsers");
        List<User> userList = userService.getAllUsers();
        return ResponseEntity.ok(userList);
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User newUser) {
        logger.info("POST request received for createUser");

        // Realizar validaciones de usuario
        ErrorResponse validationError = userValidationService.validateUserForCreate(newUser);
        if (validationError != null) {
            logger.warning("User validation failed");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationError);
        }

        User savedUser = userService.createUser(newUser);
        logger.info("User created successfully with ID: " + savedUser.getUserId());
        return ResponseEntity.ok("Creación de usuario exitosa");
    }
}


