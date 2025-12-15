package org.casa.backend.controllers;

import java.util.List;

import org.casa.backend.dto.AsistenciaAlumnoDto;
import org.casa.backend.dto.AsistenciaDto;
import org.casa.backend.service.AsistenciaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/asistencias")
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
    
    @GetMapping("/alumno/{idAlumno}")
    public List<AsistenciaDto> obtenerAsistenciasPorAlumno(@PathVariable String idAlumno) {
        return asistenciaService.obtenerAsistenciasPorAlumno(idAlumno);
    }

    @PostMapping
    public ResponseEntity<AsistenciaDto> registrar(@RequestBody AsistenciaDto dto) {
        return ResponseEntity.ok(asistenciaService.registrarAsistencia(dto));
    }

    @PutMapping ("/presente/{id}")
    public ResponseEntity<AsistenciaDto> updateAsistencia(@PathVariable("id") String asistenciaId, @RequestBody AsistenciaDto updatedAsistencia)
    {
        AsistenciaDto asistencia = asistenciaService.updateAsistencia(asistenciaId, updatedAsistencia);
        return ResponseEntity.ok(asistencia);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable String id) {
        asistenciaService.eliminarAsistencia(id);
        return ResponseEntity.noContent().build();
    }
    //Validar asistencias para constancias
    @GetMapping("/actividad/{idActividad}/aprobados")
    public ResponseEntity<List<AsistenciaAlumnoDto>> obtenerAsistencias(
            @PathVariable String idActividad) {

        return ResponseEntity.ok(
                asistenciaService.obtenerAsistencias(idActividad)
        );
    }
}
