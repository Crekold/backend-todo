package com.backend.todo.service;

import java.util.List;

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

    public Etiqueta updateEtiqueta(Etiqueta updatedEtiqueta) {
        return etiquetaRepository.save(updatedEtiqueta);
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
}
