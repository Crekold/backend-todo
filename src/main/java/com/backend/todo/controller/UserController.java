package com.backend.todo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.backend.todo.service.UserService;
import com.backend.todo.service.UserValidationService;
import com.backend.todo.model.ErrorResponse;
import com.backend.todo.model.User;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserValidationService userValidationService;
    private final UserService userService;

    public UserController( UserValidationService userValidationService, UserService userService) {
        this.userValidationService = userValidationService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> userList = userService.getAllUsers();
        return ResponseEntity.ok(userList);
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User newUser) {
        // Realizar validaciones de usuario
        ErrorResponse validationError = userValidationService.validateUserForCreate(newUser);
        if (validationError != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationError);
        }

        User savedUser = userService.createUser(newUser);
        return ResponseEntity.ok("Creación de usuario exitosa");
    }
}

