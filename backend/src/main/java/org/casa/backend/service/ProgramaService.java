package org.casa.backend.service;

import java.util.List;
import java.util.Optional;

import org.casa.backend.entity.Programa;
import org.casa.backend.repository.ProgramaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProgramaService {
    
    private final ProgramaRepository programaRepository;
    
    public ProgramaService(ProgramaRepository programaRepository) {
        this.programaRepository = programaRepository;
    }
    
    public List<Programa> listarTodos() {
        return programaRepository.findAll();
    }
    
    public List<Programa> listarTodosConDetalles() {
        return programaRepository.findAllWithDetails();
    }
    
    public Optional<Programa> obtenerPorId(String id) {
        return programaRepository.findById(id);
    }
    
    public List<Programa> obtenerPorUsuario(String idUsuario) {
        return programaRepository.findByUsuarioIdUsuario(idUsuario);
    }
    
    public List<Programa> obtenerPorNombre(String nombrePrograma) {
        return programaRepository.findByNombrePrograma(nombrePrograma);
    }
    
    @Transactional
    public Programa guardar(Programa programa) {
        // Verificar si ya existe un programa con el mismo nombre para el mismo usuario
        if (programaRepository.existsByNombreProgramaAndUsuarioIdUsuario(
                programa.getNombrePrograma(), 
                programa.getUsuario().getIdUsuario())) {
            throw new RuntimeException("Ya existe un programa con el mismo nombre para este usuario");
        }
        
        // Asegurar que el ID sea null para que el trigger lo genere
        programa.setIdPrograma(null);
        
        return programaRepository.save(programa);
    }
    
    @Transactional
    public Programa actualizar(String id, Programa programaActualizado) {
        Programa programa = programaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Programa no encontrado"));
        
        // Verificar si el nombre ya existe para otro programa del mismo usuario
        if (!programa.getNombrePrograma().equals(programaActualizado.getNombrePrograma()) &&
            programaRepository.existsByNombreProgramaAndUsuarioIdUsuario(
                programaActualizado.getNombrePrograma(), 
                programa.getUsuario().getIdUsuario())) {
            throw new RuntimeException("Ya existe un programa con el mismo nombre para este usuario");
        }
        
        programa.setNombrePrograma(programaActualizado.getNombrePrograma());
        
        return programaRepository.save(programa);
    }
    
    @Transactional
    public Programa actualizarNombre(String id, String nuevoNombre) {
        Programa programa = programaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Programa no encontrado"));
        if (!programa.getNombrePrograma().equals(nuevoNombre) &&
            programaRepository.existsByNombreProgramaAndUsuarioIdUsuario(
                nuevoNombre, 
                programa.getUsuario().getIdUsuario())) {
            throw new RuntimeException("Ya existe un programa con el mismo nombre para este usuario");
        }
        
        programa.setNombrePrograma(nuevoNombre);
        return programaRepository.save(programa);
    }
    
    public void eliminar(String id) {
        programaRepository.deleteById(id);
    }
    
    @Transactional
    public void eliminarPorUsuario(String idUsuario) {
        // ON DELETE CASCADE en la BD se encargará de esto automáticamente
        // Pero podemos implementar lógica adicional si es necesario
        List<Programa> programas = programaRepository.findByUsuarioIdUsuario(idUsuario);
        programaRepository.deleteAll(programas);
    }
    
    public boolean existePorNombreYUsuario(String nombrePrograma, String idUsuario) {
        return programaRepository.existsByNombreProgramaAndUsuarioIdUsuario(nombrePrograma, idUsuario);
    }
    
    public boolean existePorId(String id) {
        return programaRepository.existsById(id);
    }
    
    public long contarTotal() {
        return programaRepository.count();
    }
}