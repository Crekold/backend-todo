package com.backend.todo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.todo.model.Etiqueta;
import com.backend.todo.repository.EtiquetaRepository;

@RestController
@RequestMapping("/api/v1/etiqueta")
public class EtiquetaController {
    private final EtiquetaRepository etiquetaRepository;
    public EtiquetaController(EtiquetaRepository etiquetaRepository) {
        this.etiquetaRepository = etiquetaRepository;
    }
    @GetMapping
    public ResponseEntity<List<Etiqueta>> getAllEtiquetas() {
        List<Etiqueta> etiquetaList = (List<Etiqueta>) etiquetaRepository.findAll();
        return ResponseEntity.ok(etiquetaList);
    }
    
}
