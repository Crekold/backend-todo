package com.backend.todo.service.UserServices;

import java.util.List;

import org.springframework.stereotype.Service;

import com.backend.todo.model.User;
import com.backend.todo.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    public User createUser(User newUser) {
        return userRepository.save(newUser);
    }

    // Puedes agregar más métodos relacionados con usuarios aquí según tus necesidades
}
