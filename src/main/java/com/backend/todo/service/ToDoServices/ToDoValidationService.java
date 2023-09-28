package com.backend.todo.service.ToDoServices;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.backend.todo.model.ErrorResponse;
import com.backend.todo.model.ToDo;

@Service
public class ToDoValidationService {

    public ErrorResponse validateTaskForCreate(ToDo newTask) {
        if (newTask.getNameTask() == null || newTask.getNameTask().isEmpty()) {
            return new ErrorResponse("ERR1", "El nombre de la tarea es obligatorio", HttpStatus.BAD_REQUEST.value());
        }
        if (newTask.getDueDate() == null) {
            return new ErrorResponse("ERR2", "La fecha de vencimiento es obligatoria", HttpStatus.BAD_REQUEST.value());
        }
        return null; // Si la validación es exitosa, devuelve null
    }

    public ErrorResponse validateTaskForUpdate(ToDo updatedTask) {
        if (updatedTask == null) {
            return new ErrorResponse("ERR701", "El objeto updatedTask es nulo", HttpStatus.BAD_REQUEST.value());
        }
        return null; // Si la validación es exitosa, devuelve null
    }
}
