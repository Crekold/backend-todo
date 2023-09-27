package com.backend.todo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "etiquetas")
public class Etiqueta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_etiqueta")
    private int idEtiqueta;

    @Column(name = "etiqueta", length = 100, nullable = false)
    private String etiqueta;

    @Column(name = "users_id_user", nullable = false)
    private int idUsuario;

    public Etiqueta() {
    }

    public Etiqueta(int idEtiqueta, String etiqueta, int idUsuario) {
        this.idEtiqueta = idEtiqueta;
        this.etiqueta = etiqueta;
        this.idUsuario = idUsuario;
    }

    public int getIdEtiqueta() {
        return idEtiqueta;
    }

    public void setIdEtiqueta(int idEtiqueta) {
        this.idEtiqueta = idEtiqueta;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }
    public int getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
}
