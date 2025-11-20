package org.casa.backend.controllers;

import java.util.List;
import java.util.Map;

import org.casa.backend.dto.AlumnoActividadDto;
import org.casa.backend.dto.PostulacionDto;
import org.casa.backend.enums.EstadoPost;
import org.casa.backend.service.PostulacionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;


@CrossOrigin("*")
@RestController
@RequestMapping("/api/postulaciones")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class PostulacionController {

    private PostulacionService postulacionService;

    @PostMapping
    public ResponseEntity<PostulacionDto> create(@RequestBody PostulacionDto dto) {
        return new ResponseEntity<>(postulacionService.createPostulacion(dto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostulacionDto> getPostulacionById(@PathVariable("id") String idPostulacion) {
        PostulacionDto postulacionDto = postulacionService.getPostulacionById(idPostulacion);
        return ResponseEntity.ok(postulacionDto);
    }

    @GetMapping
    public ResponseEntity<List<PostulacionDto>> getAll() {
        return ResponseEntity.ok(postulacionService.getAllPostulaciones());
    }

    @GetMapping("/alumnos/{idActividad}")
    public List<AlumnoActividadDto> obtenerAlumnosPorActividad(@PathVariable String idActividad) {
        return postulacionService.getAlumnosPorActividad(idActividad);
    }

    //Editar el estado de una postulacion
    @PutMapping("/estado/{id}")
    public ResponseEntity<PostulacionDto> actualizarEstado(@PathVariable("id") String postulacionId, @RequestBody Map<String, String> body) {
        String estadoString = body.get("estadoPos");
        EstadoPost estado = EstadoPost.valueOf(estadoString.toUpperCase());

        PostulacionDto postulacionDto = postulacionService.updateEstadoPos(postulacionId, estado);
        return ResponseEntity.ok(postulacionDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        postulacionService.deletePostulacion(id);
        return ResponseEntity.noContent().build();
    }
}
