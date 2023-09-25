package com.backend.todo.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tasks") // Nombre de la tabla en la base de datos
public class ToDo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_task") // Nombre de la columna en la base de datos
    private Long taskId;

    private String name_task;
    private LocalDate dueDate;
    private String status;


    @Column(name = "users_id_user") // Nombre de la columna en la base de datos
    private int userId;
    @Column(name = "etiquetas_id_etiqueta") // Nombre de la columna en la base de datos
    private int etiquetaId;

    // Constructor vacío requerido por JPA
    public ToDo() {
    }

    // Constructor con parámetros
    public ToDo(Long taskId, String name_task, LocalDate dueDate, String status,int userId ,int etiquetaId) {
        this.taskId = taskId;
        this.name_task = name_task;
        this.dueDate = dueDate;
        this.status = status;
        this.userId = userId;
        this.etiquetaId = etiquetaId;
    }

    // Getters y setters
    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getName_task() {
        return name_task;
    }

    public void setName_task(String name_task) {
        this.name_task = name_task;
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
   public void getUserId(int userId) {
        this.userId = userId;
    }
    public void setEtiquetaId(int etiquetaId) {
        this.etiquetaId = etiquetaId;
    }
    public int getUserId() {
        return userId;
    }
    public int getEtiquetaId() {
        return etiquetaId;
    }
}