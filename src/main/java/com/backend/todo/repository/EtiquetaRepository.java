package com.backend.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.backend.todo.model.Etiqueta;
import java.util.List;


public interface EtiquetaRepository extends JpaRepository<Etiqueta, Integer>{
    public List<Etiqueta> findByEtiqueta(String etiqueta);
    public List<Etiqueta> findByIdUsuario(int idUsuario);
    
}
