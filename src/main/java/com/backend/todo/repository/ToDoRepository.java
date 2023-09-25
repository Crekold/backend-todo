package com.backend.todo.repository;
import com.backend.todo.model.ToDo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToDoRepository extends JpaRepository<ToDo, Integer> {
    ToDo findByTaskId(int taskId);
    
}