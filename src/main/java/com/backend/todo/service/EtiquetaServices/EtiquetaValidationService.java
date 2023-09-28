package com.backend.todo.service.EtiquetaServices;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.backend.todo.model.ErrorResponse;
import com.backend.todo.model.Etiqueta;
import com.backend.todo.repository.EtiquetaRepository;

@Service
public class EtiquetaValidationService {
    private final EtiquetaRepository etiquetaRepository;
    public EtiquetaValidationService(EtiquetaRepository etiquetaRepository) {
        this.etiquetaRepository = etiquetaRepository;
    }
    public ErrorResponse validateEtiqueta(Etiqueta etiqueta) {
        if (etiqueta.getEtiqueta() == null || etiqueta.getEtiqueta().isEmpty()) {
            return new ErrorResponse("ERR1", "El nombre de la etiqueta es obligatorio", HttpStatus.BAD_REQUEST.value());
        }
        return null; // Si la validación es exitosa, devuelve null
    }

    public ErrorResponse validateEtiquetaId(Integer etiquetaId) {
        if(etiquetaId == null)
            return new ErrorResponse("ERR2", "El id de la etiqueta es obligatorio", HttpStatus.BAD_REQUEST.value());
        return null;
    }
    public ErrorResponse validateEtiquetaForUpdate(int etiquetaId, Etiqueta updatedEtiqueta) {
        Optional<Etiqueta> optionalEtiqueta = etiquetaRepository.findById(etiquetaId);

        if (!optionalEtiqueta.isPresent()) {
            return new ErrorResponse("ERR2", "La etiqueta con el ID " + etiquetaId + " no existe", HttpStatus.NOT_FOUND.value());
        }

        // Realiza otras validaciones necesarias para la actualización
        // Por ejemplo, verifica que updatedEtiqueta no sea null y que cumpla con ciertos criterios

        // Si hay errores de validación, crea y devuelve un objeto ErrorResponse apropiado
        // Si no hay errores de validación, devuelve null para indicar que la validación fue exitosa

        return null;
    }
}
