package com.backend.todo.service.EtiquetaServices;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.backend.todo.model.Etiqueta;
import com.backend.todo.repository.EtiquetaRepository;

@Service
public class EtiquetaService {

    private final EtiquetaRepository etiquetaRepository;

    public EtiquetaService(EtiquetaRepository etiquetaRepository) {
        this.etiquetaRepository = etiquetaRepository;
    }

    public Etiqueta createEtiqueta(Etiqueta newEtiqueta) {
        return etiquetaRepository.save(newEtiqueta);
    }

    
    public void deleteEtiqueta(Integer id) {
        etiquetaRepository.deleteById(id);
    }
    public Etiqueta getEtiqueta(Integer id) {
        return etiquetaRepository.findById(id).orElse(null);
    }
    public List<Etiqueta> getEtiquetabyuserid (Integer userid)
    {
        return (List<Etiqueta>) etiquetaRepository.findByIdUsuario(userid);
    }

    public List<Etiqueta> getAllEtiquetas() {
        return (List<Etiqueta>) etiquetaRepository.findAll();
    }
    public Etiqueta updateEtiqueta(int etiquetaId, Etiqueta updatedEtiqueta) {
        Optional<Etiqueta> optionalEtiqueta = etiquetaRepository.findById(etiquetaId);

        if (optionalEtiqueta.isPresent()) {
            Etiqueta existingEtiqueta = optionalEtiqueta.get();

            // Realizar las modificaciones necesarias en existingEtiqueta usando los datos de updatedEtiqueta
            // Por ejemplo: existingEtiqueta.setEtiqueta(updatedEtiqueta.getEtiqueta());
            // Actualiza los campos que desees modificar
            existingEtiqueta.setEtiqueta(updatedEtiqueta.getEtiqueta());

            Etiqueta savedEtiqueta = etiquetaRepository.save(existingEtiqueta);
            return savedEtiqueta;
        } else {
            return null; // Retorna null si la etiqueta con el ID no existe
        }
    }

}
