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
public ResponseEntity<?> getTaskById(@PathVariable int taskId) {
    // Busca la tarea en la base de datos
    Optional<ToDo> optionalTask = toDoRepository.findById(taskId);

    // Verifica si la tarea existe
    if (optionalTask.isPresent()) {
        ToDo task = optionalTask.get();
        return ResponseEntity.ok(task);
    } else {
        return ResponseEntity.notFound().build();
    }

}

    @PostMapping
public ResponseEntity<?> createTask(@RequestBody ToDo newTask) {
    // Realiza las validaciones necesarias
    if (newTask.getNameTask() == null || newTask.getNameTask().isEmpty()) {
        ErrorResponse error = new ErrorResponse("ERR1","El nombre de la tarea es obligatorio", HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
    if (newTask.getDueDate() == null) {
        ErrorResponse error = new ErrorResponse("ERR1","La fecha de vencimiento es obligatoria", HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    // Guarda la tarea en la base de datos
    ToDo savedTask = toDoRepository.save(newTask);

    return ResponseEntity.ok(savedTask);
}
@PutMapping("/{taskId}")
public ResponseEntity<?> updateTask(@PathVariable int taskId, @RequestBody ToDo updatedTask) {
    // Verifica si el objeto updatedTask es nulo
    if (updatedTask == null) {
        // Devuelve un mensaje de error
        ErrorResponse errorResponse = new ErrorResponse("ERR701", "El objeto updatedTask es nulo", HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    // Busca la tarea en la base de datos
    Optional<ToDo> optionalTask = toDoRepository.findById(taskId);

    // Verifica si la tarea existe
    if (optionalTask.isPresent()) {
        ToDo task = optionalTask.get();
        
        // Actualiza el estado de la tarea con el nuevo estado
        task.setStatus(updatedTask.getStatus());

        // Guarda la tarea actualizada en la base de datos
        ToDo savedTask = toDoRepository.save(task);

        return ResponseEntity.ok(savedTask);
    } else {
        // Devuelve un mensaje de error
        ErrorResponse errorResponse = new ErrorResponse("ERR700", "La tarea con el ID " + taskId + " no existe", HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
}
@GetMapping("/user/{userId}")
public ResponseEntity<List<ToDo>> getTasksByUserId(@PathVariable int userId) {
    // Busca las tareas en la base de datos que correspondan al usuario
    List<ToDo> tasksByUserId = toDoRepository.findByUserId(userId);

    // Verifica si se encontraron tareas
    if (tasksByUserId.isEmpty()) {
        return ResponseEntity.notFound().build();
    } else {
        return ResponseEntity.ok(tasksByUserId);
    }
}

    @DeleteMapping("/{taskId}")
    public ResponseEntity<String> deleteTask(@PathVariable int taskId) {
        // Elimina la tarea de la base de datos si existe
        if (toDoRepository.existsById(taskId)) {
            toDoRepository.deleteById(taskId);
            return ResponseEntity.ok("Tarea borrada con ID: " + taskId);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}