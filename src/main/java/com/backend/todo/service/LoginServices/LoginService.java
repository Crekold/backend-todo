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

    private static final String USERNAME_ERR_CODE = "ERR1";
    private static final String USERNAME_ERR_MSG = "El nombre de usuario es obligatorio";
    private static final String PASSWORD_ERR_CODE = "ERR2";
    private static final String PASSWORD_ERR_MSG = "La contraseña es obligatoria";
    private static final String USER_NOT_FOUND_ERR_CODE = "ERR3";
    private static final String USER_NOT_FOUND_ERR_MSG = "El usuario no existe";
    private static final String INCORRECT_PASSWORD_ERR_CODE = "ERR4";
    private static final String INCORRECT_PASSWORD_ERR_MSG = "La contraseña es incorrecta";

    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ErrorResponse validateLogin(final User user) {
        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            return new ErrorResponse(USERNAME_ERR_CODE, USERNAME_ERR_MSG, HttpStatus.BAD_REQUEST.value());
        }
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            return new ErrorResponse(PASSWORD_ERR_CODE, PASSWORD_ERR_MSG, HttpStatus.BAD_REQUEST.value());
        }
        return null; // If validation is successful, return null
    }

    public ResponseEntity<?> login(User user) {
        ErrorResponse validationError = validateLogin(user);
        if (validationError != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationError);
        }

        User savedUser = userRepository.findByUsername(user.getUsername());

        if (savedUser == null) {
            ErrorResponse error = new ErrorResponse(USER_NOT_FOUND_ERR_CODE, USER_NOT_FOUND_ERR_MSG, HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }

        if (!savedUser.getPassword().equals(user.getPassword())) {
            ErrorResponse error = new ErrorResponse(INCORRECT_PASSWORD_ERR_CODE, INCORRECT_PASSWORD_ERR_MSG, HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }

        return ResponseEntity.ok(1 +", "+savedUser.getUserId());
    }
}

