package org.casa.backend.controllers;

import java.util.List;

import org.casa.backend.dto.ActividadDto;
//import org.casa.backend.entity.Actividad;
import org.casa.backend.service.ActividadService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/actividades")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ActividadController {

    private final ActividadService actividadService;

    @GetMapping
    public ResponseEntity<List<ActividadDto>> listarActividades() {
        return ResponseEntity.ok(actividadService.listarActividades());
    }

    @GetMapping("/{idActividad}")
    public ResponseEntity<ActividadDto> obtenerPorId(@PathVariable String idActividad) {
        ActividadDto actividad = actividadService.getActividadById(idActividad);
        return actividad != null ? ResponseEntity.ok(actividad) : ResponseEntity.notFound().build();
    }

    /*
    @PostMapping
    public ResponseEntity<Actividad> crearActividad(@RequestBody Actividad actividad) {
        return ResponseEntity.ok(actividadService.createActividad(actividad));
    }*/

    @DeleteMapping("/{idActividad}")
    public ResponseEntity<Void> eliminarActividad(@PathVariable String idActividad) {
        actividadService.deleteActividad(idActividad);
        return ResponseEntity.noContent().build();
    }
}
