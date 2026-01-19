package org.casa.backend.controllers;

import java.util.List;
import java.util.Map;

import org.casa.backend.dto.*;
import org.casa.backend.entity.Postulacion;
import org.casa.backend.enums.EstadoPost;
import org.casa.backend.service.PostulacionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;


@RestController
@RequestMapping("/api/postulaciones")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class PostulacionController {

    private PostulacionService postulacionService;

    @PostMapping("/taller")
    public ResponseEntity<PostulacionDto> create(@RequestBody PostulacionDto dto) {
        return new ResponseEntity<>(postulacionService.createPostulacion(dto), HttpStatus.CREATED);
    }
    @PostMapping("/convocatoria")
    public ResponseEntity<PostulacionDto> createPostulacionConvocatoria(@RequestBody PostulacionDto dto) {
        return new ResponseEntity<>(postulacionService.createPostulacionConvocatoria(dto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostulacionDto> getPostulacionById(@PathVariable("id") String idPostulacion) {
        PostulacionDto postulacionDto = postulacionService.getPostulacionById(idPostulacion);
        return ResponseEntity.ok(postulacionDto);
    }
    @GetMapping("/actividad/{id}")
    public ResponseEntity<List<PostulacionDto>> obtenerPostulacionesByActividad(
        @PathVariable("id") String idActividad){
            return ResponseEntity.ok(postulacionService.getPostulacionesByActividad(idActividad));
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

    @GetMapping("/pendientes/{idActividad}")
    public ResponseEntity<List<PostulacionDto>> obtenerPendientesPorActividad(
            @PathVariable String idActividad) {

        List<PostulacionDto> pendientes = postulacionService.getPostulacionesPendientes(idActividad);
        return ResponseEntity.ok(pendientes);
    }

    @GetMapping("/pendientes/actividad/{idActividad}")
    public ResponseEntity<List<PostulacionParticipanteDto>> getPendientesParticipante(
            @PathVariable String idActividad) {

        return ResponseEntity.ok(
                postulacionService.getPostulacionesPendientesParticipante(idActividad)
        );
    }

    @PostMapping("/seleccionar/{idActividad}")
    public ResponseEntity<Void> seleccionarPostulantes(
            @PathVariable String idActividad,
            @RequestBody List<String> postulacionesAprobadasIds) {

        postulacionService.seleccionarPostulantes(idActividad, postulacionesAprobadasIds);
        return ResponseEntity.ok().build();
    }
    /* @GetMapping("/pendientes/jurado")
    public ResponseEntity<List<PostulacionDto>> getPendientesParaJurado(
            @RequestParam String idJurado,
            @RequestParam Integer ronda
    ) {
        return ResponseEntity.ok(
                postulacionService.getPendientesParaJuradoIds(
                        idJurado,
                        ronda
                )
        );
    }*/

    @GetMapping("/pendientes/jurado")
    public ResponseEntity<List<PostulacionPendienteJuradoDto>> getPendientesParaJurado(
            @RequestParam String idJurado,
            @RequestParam Integer ronda
    ) {
        return ResponseEntity.ok(
                postulacionService.getPendientesParaJurado(idJurado, ronda)
        );
    }

    @PostMapping("/convocatoria/registro-postal")
    public ResponseEntity<Postulacion> registrarPostulacionPostal(
            @ModelAttribute RegistroPostalPostulacionDto dto
    ) {
        Postulacion postulacion = postulacionService.registrarPostulacionPostal(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(postulacion);
    }


}
