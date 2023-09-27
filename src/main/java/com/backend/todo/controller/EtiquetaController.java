package com.backend.todo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.todo.model.ErrorResponse;
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
    @PostMapping
    public ResponseEntity<?> createEtiqueta(@RequestBody Etiqueta newEtiqueta) {
        // Realiza las validaciones necesarias teniendo en cuenta que solo tengo dos campos
        if (newEtiqueta.getEtiqueta() == null || newEtiqueta.getEtiqueta().isEmpty()) {
            ErrorResponse error = new ErrorResponse("ERR1","El nombre de la etiqueta es obligatorio", HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.badRequest().body(error);
        }
        Etiqueta savedEtiqueta = etiquetaRepository.save(newEtiqueta);
        return ResponseEntity.ok(savedEtiqueta);
    }
    @PutMapping
    public ResponseEntity<?> updateEtiqueta(@RequestBody Etiqueta newEtiqueta) {
        // Realiza las validaciones necesarias teniendo en cuenta que solo tengo dos campos
        if (newEtiqueta.getEtiqueta() == null || newEtiqueta.getEtiqueta().isEmpty()) {
            ErrorResponse error = new ErrorResponse("ERR1","El nombre de la etiqueta es obligatorio", HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.badRequest().body(error);
        }
        Etiqueta savedEtiqueta = etiquetaRepository.save(newEtiqueta);
        return ResponseEntity.ok(savedEtiqueta);
    }
    @DeleteMapping("/{etiquetaId}")
    public ResponseEntity<?> deleteEtiqueta(@PathVariable int etiquetaId) {
    // Busca la etiqueta por su ID en la base de datos
    Optional<Etiqueta> optionalEtiqueta = etiquetaRepository.findById(etiquetaId);

    // Verifica si se encontró la etiqueta
    if (optionalEtiqueta.isPresent()) {
        // Elimina la etiqueta de la base de datos
        etiquetaRepository.deleteById(etiquetaId);

        return ResponseEntity.noContent().build();
    } else {
        // Devuelve un mensaje de error
        ErrorResponse errorResponse = new ErrorResponse("ERR2", "La etiqueta con el ID " + etiquetaId + " no existe", HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
}
}
