package com.backend.todo.service.EtiquetaServices;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.backend.todo.model.ErrorResponse;
import com.backend.todo.model.Etiqueta;

@Service
public class EtiquetaValidationService {

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
}
