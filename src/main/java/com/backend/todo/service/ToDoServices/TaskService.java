package com.backend.todo.service.ToDoServices;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.backend.todo.model.ToDo;
import com.backend.todo.repository.ToDoRepository;

@Service
public class TaskService {

    private final ToDoRepository toDoRepository;

    public TaskService(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }

    public List<ToDo> getAllTasks() {
        return (List<ToDo>) toDoRepository.findAll();
    }

    public Optional<ToDo> getTaskById(int taskId) {
        return toDoRepository.findById(taskId);
    }

    public ToDo createTask(ToDo newTask) {
        return toDoRepository.save(newTask);
    }

    public Optional<ToDo> updateTask(int taskId, ToDo updatedTask) {
        Optional<ToDo> optionalTask = toDoRepository.findById(taskId);

        if (optionalTask.isPresent()) {
            ToDo task = optionalTask.get();
            task.setStatus(updatedTask.getStatus());
            return Optional.of(toDoRepository.save(task));
        } else {
            return Optional.empty();
        }
    }

    public List<ToDo> getTasksByUserId(int userId) {
        return toDoRepository.findByUserId(userId);
    }

    public boolean deleteTask(int taskId) {
        if (toDoRepository.existsById(taskId)) {
            toDoRepository.deleteById(taskId);
            return true;
        } else {
            return false;
        }
    }
}
