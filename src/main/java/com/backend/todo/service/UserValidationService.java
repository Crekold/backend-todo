package com.backend.todo.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.backend.todo.model.ErrorResponse;
import com.backend.todo.model.User;

@Service
public class UserValidationService {

    public ErrorResponse validateUserForCreate(User newUser) {
        if (newUser.getUsername() == null || newUser.getUsername().isEmpty()) {
            return new ErrorResponse("ERR1", "El nombre de usuario es obligatorio", HttpStatus.BAD_REQUEST.value());
        }
        return null; // Si la validación es exitosa, devuelve null
    }
}
