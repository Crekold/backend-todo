package com.backend.todo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Logger;
import com.backend.todo.model.ErrorResponse;
import com.backend.todo.model.Etiqueta;
import com.backend.todo.service.EtiquetaValidationService;
import com.backend.todo.service.EtiquetaService;
@RestController
@RequestMapping("/api/v1/etiqueta")
public class EtiquetaController {

    private final EtiquetaService etiquetaService;
    private final EtiquetaValidationService etiquetaValidationService;
    private final Logger logger = Logger.getLogger(EtiquetaController.class.getName());

    public EtiquetaController(EtiquetaService etiquetaService, EtiquetaValidationService etiquetaValidationService) {
        this.etiquetaService = etiquetaService;
        this.etiquetaValidationService = etiquetaValidationService;
    }


     @GetMapping
    public ResponseEntity<List<Etiqueta>> getAllEtiquetas() {
        logger.info("GET request received for getAllEtiquetas");
        List<Etiqueta> etiquetaList = etiquetaService.getAllEtiquetas();
        return ResponseEntity.ok(etiquetaList);
    }

    @PostMapping
    public ResponseEntity<?> createEtiqueta(@RequestBody Etiqueta newEtiqueta) {
        logger.info("POST request received for createEtiqueta");
        
        // Validar la etiqueta
        ErrorResponse validationError = etiquetaValidationService.validateEtiqueta(newEtiqueta);
        if (validationError != null) {
            logger.warning("Invalid request received for createEtiqueta");
            return ResponseEntity.badRequest().body(validationError);
        }

        Etiqueta savedEtiqueta = etiquetaService.createEtiqueta(newEtiqueta);
        logger.info("Etiqueta created successfully");
        return ResponseEntity.ok(savedEtiqueta);
    }

    @PutMapping
    public ResponseEntity<?> updateEtiqueta(@RequestBody Etiqueta updatedEtiqueta) {
        logger.info("PUT request received for updateEtiqueta");

        // Validar la etiqueta
        ErrorResponse validationError = etiquetaValidationService.validateEtiqueta(updatedEtiqueta);
        if (validationError != null) {
            logger.warning("Invalid request received for updateEtiqueta");
            return ResponseEntity.badRequest().body(validationError);
        }

        Etiqueta savedEtiqueta = etiquetaService.updateEtiqueta(updatedEtiqueta);
        logger.info("Etiqueta updated successfully");
        return ResponseEntity.ok(savedEtiqueta);
    }
    @DeleteMapping("/{etiquetaId}")
    public ResponseEntity<?> deleteEtiqueta(@PathVariable Integer etiquetaId) {
        logger.info("DELETE request received for deleteEtiqueta");

        // Validar la etiqueta
        ErrorResponse validationError = etiquetaValidationService.validateEtiquetaId(etiquetaId);
        if (validationError != null) {
            logger.warning("Invalid request received for deleteEtiqueta");
            return ResponseEntity.badRequest().body(validationError);
        }

        etiquetaService.deleteEtiqueta(etiquetaId);
        logger.info("Etiqueta deleted successfully");
        return ResponseEntity.ok("Etiqueta deleted successfully");
    }
}