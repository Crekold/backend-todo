package com.backend.todo.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tasks")
public class ToDo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_task")
    private int taskId; // Cambiado a tipo int para que coincida con la base de datos

    @Column(name = "name_task", length = 200, nullable = false)
    private String nameTask;

    @Column(name = "due_date", nullable = false)
    private LocalDate dueDate;

    @Column(name = "status", length = 30, nullable = false)
    private String status;

    @Column(name = "users_id_user")
    private int userId;

    @Column(name = "etiquetas_id_etiqueta")
    private int etiquetaId;

    public ToDo() {
    }

    public ToDo(int taskId, String nameTask, LocalDate dueDate, String status, int userId, int etiquetaId) {
        this.taskId = taskId;
        this.nameTask = nameTask;
        this.dueDate = dueDate;
        this.status = status;
        this.userId = userId;
        this.etiquetaId = etiquetaId;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getNameTask() {
        return nameTask;
    }

    public void setNameTask(String nameTask) {
        this.nameTask = nameTask;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getEtiquetaId() {
        return etiquetaId;
    }

    public void setEtiquetaId(int etiquetaId) {
        this.etiquetaId = etiquetaId;
    }
}
