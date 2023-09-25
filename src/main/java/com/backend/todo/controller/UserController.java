package com.backend.todo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.backend.todo.repository.UserRepository;
import com.backend.todo.model.ErrorResponse;
import com.backend.todo.model.User;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> userList = (List<User>) userRepository.findAll();
        return ResponseEntity.ok(userList);
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User newUser) {
        // Realiza las validaciones necesarias teniendo en cuenta que solo tengo dos campos
        if (newUser.getUsername() == null || newUser.getUsername().isEmpty()) {
            ErrorResponse error = new ErrorResponse("El nombre de usuario es obligatorio", HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
        User savedUser = userRepository.save(newUser);
        return ResponseEntity.ok("creacion de usuario exitosa");
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        // Realiza las validaciones necesarias teniendo en cuenta que solo tengo dos campos
        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            ErrorResponse error = new ErrorResponse("El nombre de usuario es obligatorio", HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            ErrorResponse error = new ErrorResponse("La contraseña es obligatoria", HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
        User savedUser = userRepository.findByUsername(user.getUsername());
        if (savedUser == null) {
            ErrorResponse error = new ErrorResponse("El usuario no existe", HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
        if (!savedUser.getPassword().equals(user.getPassword())) {
            ErrorResponse error = new ErrorResponse("La contraseña es incorrecta", HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
        return ResponseEntity.ok("inicio de sesion exitoso");
    }

}
