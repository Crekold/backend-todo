package com.backend.todo.repository;
import com.backend.todo.model.ToDo;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToDoRepository extends CrudRepository<ToDo, Long> {
    Optional<ToDo> findByTaskId(int taskId);
}