package com.backend.todo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.backend.todo.repository.UserRepository;
import com.backend.todo.model.ErrorResponse;
import com.backend.todo.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/login")
public class LoginController {
    private final UserRepository userRepository;
    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @PostMapping
    public ResponseEntity<?> login(@RequestBody User user) {
        // Realiza las validaciones necesarias teniendo en cuenta que solo tengo dos campos
        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            ErrorResponse error = new ErrorResponse("ERR1","El nombre de usuario es obligatorio", HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            ErrorResponse error = new ErrorResponse("ERR1","La contraseña es obligatoria", HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
        User savedUser = userRepository.findByUsername(user.getUsername());
        if (savedUser == null) {
            ErrorResponse error = new ErrorResponse("ERR2","El usuario no existe", HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
        if (!savedUser.getPassword().equals(user.getPassword())) {
            ErrorResponse error = new ErrorResponse("ERR3","La contraseña es incorrecta", HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
        return ResponseEntity.ok("Login exitoso");
    }
}
