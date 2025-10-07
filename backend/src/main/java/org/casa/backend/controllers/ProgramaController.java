package org.casa.backend.controllers;

import java.util.List;

import org.casa.backend.entity.Programa;
import org.casa.backend.service.ProgramaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/programas")
public class ProgramaController {
    
    private final ProgramaService programaService;
    
    public ProgramaController(ProgramaService programaService) {
        this.programaService = programaService;
    }
    
    @GetMapping
    public List<Programa> listar() {
        return programaService.listarTodos();
    }
    
    @GetMapping("/detalles")
    public List<Programa> listarConDetalles() {
        return programaService.listarTodosConDetalles();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Programa> obtener(@PathVariable String id) {
        return programaService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/usuario/{idUsuario}")
    public List<Programa> obtenerPorUsuario(@PathVariable String idUsuario) {
        return programaService.obtenerPorUsuario(idUsuario);
    }
    
    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Programa programa) {
        try {
            programa.setIdPrograma(null);
            
            Programa programaGuardado = programaService.guardar(programa);
            return ResponseEntity.ok(programaGuardado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable String id, @RequestBody Programa programa) {
        try {
            Programa programaActualizado = programaService.actualizar(id, programa);
            return ResponseEntity.ok(programaActualizado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
    
    @PutMapping("/{id}/nombre")
    public ResponseEntity<?> actualizarNombre(@PathVariable String id, @RequestParam String nombre) {
        try {
            Programa programaActualizado = programaService.actualizarNombre(id, nombre);
            return ResponseEntity.ok(programaActualizado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable String id) {
        try {
            programaService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/usuario/{idUsuario}")
    public ResponseEntity<Void> eliminarPorUsuario(@PathVariable String idUsuario) {
        try {
            programaService.eliminarPorUsuario(idUsuario);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/verificar")
    public ResponseEntity<Boolean> verificarPrograma(
            @RequestParam String nombrePrograma, 
            @RequestParam String idUsuario) {
        boolean existe = programaService.existePorNombreYUsuario(nombrePrograma, idUsuario);
        return ResponseEntity.ok(existe);
    }
    
    @GetMapping("/contador/total")
    public ResponseEntity<Long> contarTotal() {
        long total = programaService.contarTotal();
        return ResponseEntity.ok(total);
    }
}