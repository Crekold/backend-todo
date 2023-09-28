package com.backend.todo.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.backend.todo.model.ErrorResponse;
import com.backend.todo.model.User;

@Service
public class LoginValidationService {

    public ErrorResponse validateLogin(User user) {
        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            return new ErrorResponse("ERR1", "El nombre de usuario es obligatorio", HttpStatus.BAD_REQUEST.value());
        }
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            return new ErrorResponse("ERR2", "La contraseña es obligatoria", HttpStatus.BAD_REQUEST.value());
        }
        return null; // Si la validación es exitosa, devuelve null
    }
}