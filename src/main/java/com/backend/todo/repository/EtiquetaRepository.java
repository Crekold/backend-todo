package com.backend.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.backend.todo.model.Etiqueta;

public interface EtiquetaRepository extends JpaRepository<Etiqueta, Integer>{
}
