package com.backend.todo.repository;

import com.backend.todo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    // Método para buscar un usuario por nombre de usuario
    public User findByUsername(String username);
    public User findByUsernameAndPassword(String username, String password);
}