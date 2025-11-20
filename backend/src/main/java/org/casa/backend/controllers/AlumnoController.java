package org.casa.backend.controllers;

import java.util.List;

import org.casa.backend.dto.AlumnoDto;
import org.casa.backend.dto.AlumnoTallerDto;
import org.casa.backend.service.AlumnoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/alumnos")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AlumnoController {

    private final AlumnoService alumnoService;

    @GetMapping
    public ResponseEntity<List<AlumnoDto>> listarAlumnos() {
        return ResponseEntity.ok(alumnoService.getAllAlumnos());
    }

    @GetMapping("/{idAlumno}")
    public ResponseEntity<AlumnoDto> obtenerPorId(@PathVariable String idAlumno) {
        AlumnoDto alumno = alumnoService.getAlumnoById(idAlumno);
        return alumno != null ? ResponseEntity.ok(alumno) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<AlumnoDto> crearAlumno(@RequestBody AlumnoDto alumnoDto) {
        return ResponseEntity.ok(alumnoService.createAlumno(alumnoDto));
    }

    @DeleteMapping("/{idAlumno}")
    public ResponseEntity<Void> eliminarAlumno(@PathVariable String idAlumno) {
        alumnoService.deleteAlumno(idAlumno);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/postulacion/{idPostulacion}")
    public ResponseEntity<List<AlumnoDto>> obtenerPorPostulacion(@PathVariable String idPostulacion) {
        return ResponseEntity.ok(alumnoService.getAlumnosByPostulacion(idPostulacion));
    }

    @GetMapping("/{idUsuario}/talleres")
    public ResponseEntity<List<AlumnoTallerDto>> obtenerTalleres(
            @PathVariable String idUsuario) {
        return ResponseEntity.ok(alumnoService.obtenerTalleresDeAlumno(idUsuario));
    }
}