package com.backend.todo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;
import com.backend.todo.model.ErrorResponse;
import com.backend.todo.model.Etiqueta;
import com.backend.todo.service.EtiquetaServices.EtiquetaService;
import com.backend.todo.service.EtiquetaServices.EtiquetaValidationService;
@CrossOrigin(origins = "*, maxAge = 3600")
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
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Etiqueta>> getAllEtiquetasByUserId(@PathVariable Integer userId) {
        logger.info("GET request received for getAllEtiquetasByUserId");
        List<Etiqueta> etiquetaList = etiquetaService.getEtiquetabyuserid(userId);
        if (etiquetaList.isEmpty()) {
            logger.warning("Etiquetas not found with userId: " + userId);
            return ResponseEntity.notFound().build();
        }
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
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEtiqueta);
    }

    @PutMapping("/{etiquetaId}")
    public ResponseEntity<?> updateEtiqueta(@PathVariable int etiquetaId, @RequestBody Etiqueta updatedEtiqueta) {
        logger.info("PUT request received for updateEtiqueta with etiquetaId: " + etiquetaId);
    
        // Validar la etiqueta usando el servicio de validación
        ErrorResponse validationError = etiquetaValidationService.validateEtiquetaForUpdate(etiquetaId, updatedEtiqueta);
        if (validationError != null) {
            logger.warning("Invalid request received for updateEtiqueta");
            return ResponseEntity.badRequest().body(validationError);
        }
    
        Etiqueta savedEtiqueta = etiquetaService.updateEtiqueta(etiquetaId, updatedEtiqueta);
        if (savedEtiqueta != null) {
            logger.info("Etiqueta updated successfully");
            return ResponseEntity.ok(savedEtiqueta);
        } else {
            logger.warning("Etiqueta not found with etiquetaId: " + etiquetaId);
            ErrorResponse errorResponse = new ErrorResponse("ERR2", "La etiqueta con el ID " + etiquetaId + " no existe", HttpStatus.NOT_FOUND.value());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
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