package com.backend.todo.controller;
import java.util.List;
import java.util.Optional;

import com.backend.todo.model.ErrorResponse;
import com.backend.todo.model.ToDo;
import com.backend.todo.repository.ToDoRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/task")
public class ToDoController {
    private final ToDoRepository toDoRepository;

    public ToDoController(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }

    @GetMapping
    public ResponseEntity<List<ToDo>> getAllTasks() {
        List<ToDo> taskList = (List<ToDo>) toDoRepository.findAll();
        return ResponseEntity.ok(taskList);
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<ToDo> getTaskById(@PathVariable int taskId) {
        Optional<ToDo> task = toDoRepository.findByTaskId(taskId);
        if (task.isPresent()) {
            return ResponseEntity.ok(task.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
public ResponseEntity<?> createTask(@RequestBody ToDo newTask) {
    // Realiza las validaciones necesarias
    if (newTask.getName_task() == null || newTask.getName_task().isEmpty()) {
        ErrorResponse error = new ErrorResponse("El nombre de la tarea es obligatorio", HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
    if (newTask.getDueDate() == null) {
        ErrorResponse error = new ErrorResponse("La fecha de vencimiento es obligatoria", HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    // Guarda la tarea en la base de datos
    ToDo savedTask = toDoRepository.save(newTask);

    return ResponseEntity.ok(savedTask);
}


    @DeleteMapping("/{taskId}")
    public ResponseEntity<String> deleteTask(@PathVariable Long taskId) {
        // Elimina la tarea de la base de datos si existe
        if (toDoRepository.existsById(taskId)) {
            toDoRepository.deleteById(taskId);
            return ResponseEntity.ok("Tarea borrada con ID: " + taskId);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}