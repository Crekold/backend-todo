package com.backend.todo.service.LoginServices;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.backend.todo.model.ErrorResponse;
import com.backend.todo.model.User;
import com.backend.todo.repository.UserRepository;

@Service
public class LoginService {

    private final UserRepository userRepository;

    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<?> login(User user) {
        User savedUser = userRepository.findByUsername(user.getUsername());

        if (savedUser == null) {
            ErrorResponse error = new ErrorResponse("ERR2", "El usuario no existe", HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
        
        if (!savedUser.getPassword().equals(user.getPassword())) {
            ErrorResponse error = new ErrorResponse("ERR3", "La contraseña es incorrecta", HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }

        return ResponseEntity.ok("login exitoso");
    }
}
