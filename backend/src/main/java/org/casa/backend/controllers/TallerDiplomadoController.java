package org.casa.backend.controllers;

import lombok.AllArgsConstructor;
import org.casa.backend.dto.TallerDiplomadoDto;
import org.casa.backend.service.TallerDiplomadoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<TallerDiplomadoDto>> getAllTalleresDiplomados() {
        List<TallerDiplomadoDto> talleresDiplomados = tallerDiplomadoService.getAllTalleresDiplomados();
        return ResponseEntity.ok(talleresDiplomados);
    }

    //Editar una actividad
    @PutMapping("/actividad/{id}")
    public ResponseEntity<TallerDiplomadoDto> updateActividad(@PathVariable("id") String actividadId, @RequestBody TallerDiplomadoDto updatedActividad){
        TallerDiplomadoDto actividad = tallerDiplomadoService.updateActividad(actividadId, updatedActividad);
        return ResponseEntity.ok(actividad);
    }
    //editar el taller o el diplomado
    @PutMapping("/{id}")
    public ResponseEntity<TallerDiplomadoDto> updateTallerDiplomado(@PathVariable("id") String tallerId, @RequestBody TallerDiplomadoDto updatedTallerDipl){
        TallerDiplomadoDto tallerDipDto = tallerDiplomadoService.updateTallerDiplo(tallerId, updatedTallerDipl);
        return ResponseEntity.ok(tallerDipDto);
    }
}