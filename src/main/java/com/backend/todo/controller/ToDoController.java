package com.backend.todo.controller;
import java.util.List;
import java.util.Optional;

import com.backend.todo.model.ErrorResponse;
import com.backend.todo.model.ToDo;
import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.backend.todo.service.ToDoValidationService;
import com.backend.todo.service.TaskService;

@RestController
@RequestMapping("/api/v1/task")
public class ToDoController {

    private final TaskService taskService;
    private final ToDoValidationService toDoValidationService;
    private final Logger logger = Logger.getLogger(ToDoController.class.getName());

    public ToDoController(TaskService taskService, ToDoValidationService toDoValidationService) {
        this.taskService = taskService;
        this.toDoValidationService = toDoValidationService;
    }

    @GetMapping
    public ResponseEntity<List<ToDo>> getAllTasks() {
        logger.info("GET request received for getAllTasks");
        List<ToDo> taskList = taskService.getAllTasks();
        return ResponseEntity.ok(taskList);
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<?> getTaskById(@PathVariable int taskId) {
        logger.info("GET request received for getTaskById with taskId: " + taskId);
        Optional<ToDo> optionalTask = taskService.getTaskById(taskId);

        if (optionalTask.isPresent()) {
            ToDo task = optionalTask.get();
            return ResponseEntity.ok(task);
        } else {
            logger.warning("Task not found with taskId: " + taskId);
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> createTask(@RequestBody ToDo newTask) {
        logger.info("POST request received for createTask");
        
        // Validar la tarea
        ErrorResponse validationError = toDoValidationService.validateTaskForCreate(newTask);
        if (validationError != null) {
            logger.warning("Invalid request received for createTask");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationError);
        }

        ToDo savedTask = taskService.createTask(newTask);
        logger.info("Task created successfully with ID: " + savedTask.getTaskId());
        return ResponseEntity.ok(savedTask);
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<?> updateTask(@PathVariable int taskId, @RequestBody ToDo updatedTask) {
        logger.info("PUT request received for updateTask with taskId: " + taskId);
        
        // Validar la tarea para actualizar
        ErrorResponse validationError = toDoValidationService.validateTaskForUpdate(updatedTask);
        if (validationError != null) {
            logger.warning("Invalid request received for updateTask");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationError);
        }

        Optional<ToDo> optionalTask = taskService.updateTask(taskId, updatedTask);

        if (optionalTask.isPresent()) {
            ToDo savedTask = optionalTask.get();
            logger.info("Task updated successfully with ID: " + savedTask.getTaskId());
            return ResponseEntity.ok(savedTask);
        } else {
            logger.warning("Task not found with taskId: " + taskId);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ToDo>> getTasksByUserId(@PathVariable int userId) {
        logger.info("GET request received for getTasksByUserId with userId: " + userId);
        List<ToDo> tasksByUserId = taskService.getTasksByUserId(userId);

        if (tasksByUserId.isEmpty()) {
            logger.warning("Tasks not found for userId: " + userId);
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(tasksByUserId);
        }
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<String> deleteTask(@PathVariable int taskId) {
        logger.info("DELETE request received for deleteTask with taskId: " + taskId);
        boolean deleted = taskService.deleteTask(taskId);

        if (deleted) {
            logger.info("Task deleted successfully with ID: " + taskId);
            return ResponseEntity.ok("Tarea borrada con ID: " + taskId);
        } else {
            logger.warning("Task not found with taskId: " + taskId);
            return ResponseEntity.notFound().build();
        }
    }
}
