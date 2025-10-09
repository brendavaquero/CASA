package org.casa.backend.controllers;

import java.util.List;

import org.casa.backend.dto.TallerDiplomadoDto;
import org.casa.backend.service.TallerDiplomadoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/talleres")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TallerDiplomadoController {

    private final TallerDiplomadoService tallerService;

    @GetMapping
    public ResponseEntity<List<TallerDiplomadoDto>> listarTalleres() {
        return ResponseEntity.ok(tallerService.listarTalleres());
    }

    @GetMapping("/{idActividad}")
    public ResponseEntity<TallerDiplomadoDto> obtenerPorId(@PathVariable String idActividad) {
        TallerDiplomadoDto taller = tallerService.getTallerById(idActividad);
        return taller != null ? ResponseEntity.ok(taller) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<TallerDiplomadoDto> crearTaller(@RequestBody TallerDiplomadoDto tallerDto) {
        return ResponseEntity.ok(tallerService.createTaller(tallerDto));
    }

    @DeleteMapping("/{idActividad}")
    public ResponseEntity<Void> eliminarTaller(@PathVariable String idActividad) {
        tallerService.deleteTaller(idActividad);
        return ResponseEntity.noContent().build();
    }
}
