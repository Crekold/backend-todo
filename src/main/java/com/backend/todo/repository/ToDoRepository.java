package com.backend.todo.repository;
import com.backend.todo.model.ToDo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToDoRepository extends JpaRepository<ToDo, Integer> {
    public ToDo findByTaskId(int taskId);
    public List<ToDo> findByUserId(int userId);
}