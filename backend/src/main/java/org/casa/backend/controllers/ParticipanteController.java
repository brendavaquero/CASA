package org.casa.backend.controllers;

import java.util.List;

import org.casa.backend.dto.ParticipanteDto;
//import org.casa.backend.entity.Participante;
import org.casa.backend.service.ParticipanteService;
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

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/participantes")
public class ParticipanteController {
    
    private ParticipanteService participanteService;

    @PostMapping
    public ResponseEntity<ParticipanteDto> createParticipante(@RequestBody ParticipanteDto participanteDto) {
        ParticipanteDto savedParticipante = participanteService.createParticipante(participanteDto);
        return new ResponseEntity<>(savedParticipante, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParticipanteDto> getParticipanteById(@PathVariable("id") String idParticipante) {
        ParticipanteDto participanteDto = participanteService.getParticipanteById(idParticipante);
        return ResponseEntity.ok(participanteDto);
    }

    @GetMapping
    public ResponseEntity<List<ParticipanteDto>> getAllParticipantes() {
        List<ParticipanteDto> participantes = participanteService.getAllParticipantes();
        return ResponseEntity.ok(participantes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParticipanteDto> updateParticipante(
            @PathVariable("id") String idParticipante,
            @RequestBody ParticipanteDto participanteDto) {
        ParticipanteDto updatedParticipante = participanteService.updateParticipante(idParticipante, participanteDto);
        return ResponseEntity.ok(updatedParticipante);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteParticipante(@PathVariable("id") String idParticipante) {
        participanteService.deleteParticipante(idParticipante);
        return ResponseEntity.ok("Participante eliminado correctamente con id: " + idParticipante);
    }

    /*
    @GetMapping
    public List<Participante> listar() {
        return participanteService.listarTodos();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Participante> obtener(@PathVariable String id) {
        return participanteService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/curp/{curp}")
    public ResponseEntity<Participante> obtenerPorCurp(@PathVariable String curp) {
        return participanteService.obtenerPorCurp(curp)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Participante participante) {
        try {
            if (participante.getCurp() != null && participanteService.existePorCurp(participante.getCurp())) {
                return ResponseEntity.badRequest()
                        .body("El CURP ya está registrado");
            }
            participante.setIdUsuario(null);
            
            Participante participanteGuardado = participanteService.guardar(participante);
            return ResponseEntity.ok(participanteGuardado);
            
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body("Error al crear participante: " + e.getMessage());
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Participante> actualizar(@PathVariable String id, @RequestBody Participante participante) {
        return participanteService.obtenerPorId(id).map(p -> {
            participante.setIdUsuario(id);
            return ResponseEntity.ok(participanteService.guardar(participante));
        }).orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable String id) {
        if (participanteService.obtenerPorId(id).isPresent()) {
            participanteService.eliminar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }*/
}
