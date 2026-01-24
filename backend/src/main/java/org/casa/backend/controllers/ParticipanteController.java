package org.casa.backend.controllers;

import java.util.List;

import jakarta.validation.Valid;
import org.casa.backend.dto.ParticipanteDto;
//import org.casa.backend.entity.Participante;
import org.casa.backend.dto.RegistroPostalDto;
import org.casa.backend.entity.Participante;
import org.casa.backend.service.ParticipanteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
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
//@PreAuthorize("hasRole('ADMINISTRADOR')")
@CrossOrigin(origins = "*")
public class ParticipanteController {
    
    private ParticipanteService participanteService;

    @PostMapping
    public ResponseEntity<ParticipanteDto> createParticipante(@RequestBody ParticipanteDto participanteDto) {
        ParticipanteDto savedParticipante = participanteService.createParticipante(participanteDto);
        return new ResponseEntity<>(savedParticipante, HttpStatus.CREATED);
    }

    @PostMapping("/publico")
    public ResponseEntity<ParticipanteDto> createParticipantePublico(@RequestBody ParticipanteDto participanteDto) {
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

    @PostMapping("/registro-postal")
    public ResponseEntity<Participante> registrarParticipantePostal(
            @Valid @RequestBody RegistroPostalDto dto
    ) {
        Participante participante = participanteService
                .registrarParticipantePostal(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(participante);
    }
}
