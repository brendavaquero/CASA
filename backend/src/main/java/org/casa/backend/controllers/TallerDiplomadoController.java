package org.casa.backend.controllers;

import lombok.AllArgsConstructor;
import org.casa.backend.dto.TallerDiplomadoDto;
import org.casa.backend.service.TallerDiplomadoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/talleresydiplomados")
public class TallerDiplomadoController {
    private TallerDiplomadoService tallerDiplomadoService;

    //Rest API ADD
    @PostMapping
    public ResponseEntity<TallerDiplomadoDto> createTallerDiplomado(@RequestBody TallerDiplomadoDto tallerDiplomadoDto) {
        TallerDiplomadoDto savedTallerDiplomado = tallerDiplomadoService.createTallerDiplomado(tallerDiplomadoDto);
        return new ResponseEntity<>(savedTallerDiplomado, HttpStatus.CREATED);
    }

    //Rest API GET
    @GetMapping("{id}")
    public ResponseEntity<TallerDiplomadoDto> getTallerDiplomadoById(@PathVariable("id") String idActividad) {
        TallerDiplomadoDto tallerDiplomadoDto = tallerDiplomadoService.getTallerDiplomadoById(idActividad);
        return ResponseEntity.ok(tallerDiplomadoDto);
    }
}
