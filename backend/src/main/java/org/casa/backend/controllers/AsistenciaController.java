package org.casa.backend.controllers;

import java.util.List;
import org.casa.backend.dto.AsistenciaDto;
import org.casa.backend.service.AsistenciaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/asistencias")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AsistenciaController {

    private final AsistenciaService asistenciaService;

    @GetMapping
    public ResponseEntity<List<AsistenciaDto>> listar() {
        return ResponseEntity.ok(asistenciaService.listarAsistencias());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AsistenciaDto> obtenerPorId(@PathVariable String id) {
        return ResponseEntity.ok(asistenciaService.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<AsistenciaDto> registrar(@RequestBody AsistenciaDto dto) {
        return ResponseEntity.ok(asistenciaService.registrarAsistencia(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable String id) {
        asistenciaService.eliminarAsistencia(id);
        return ResponseEntity.noContent().build();
    }
}
