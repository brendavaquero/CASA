package org.casa.backend.controllers;

import lombok.AllArgsConstructor;
import org.casa.backend.dto.SesionDto;
import org.casa.backend.service.SesionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/sesiones")
public class SesionController {
    private SesionService sesionService;

    @PostMapping
    public ResponseEntity<SesionDto> createSesion(@RequestBody SesionDto sesionDto) {
        SesionDto savedSesion = sesionService.createSesion(sesionDto);
        return new ResponseEntity<>(savedSesion, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SesionDto> getSesionById(@PathVariable("id") String idSesion) {
        SesionDto sesionDto = sesionService.geSesionById(idSesion);
        return ResponseEntity.ok(sesionDto);
    }

    // obtener todas las sesiones de un taller/diplomado
    @GetMapping("/tallerdiplomado/{idTaller}")
    public ResponseEntity<List<SesionDto>> getSesionesByTaller(@PathVariable("idTaller") String idTallerDiplomado) {
        List<SesionDto> sesiones = sesionService.getSesionesByTaller(idTallerDiplomado);
        return ResponseEntity.ok(sesiones);
    }
}
