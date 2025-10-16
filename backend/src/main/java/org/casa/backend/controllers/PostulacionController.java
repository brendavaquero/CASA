package org.casa.backend.controllers;

import java.util.List;

import org.casa.backend.dto.PostulacionDto;
import org.casa.backend.dto.TallerDiplomadoDto;
import org.casa.backend.service.PostulacionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/postulaciones")
@AllArgsConstructor
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        postulacionService.deletePostulacion(id);
        return ResponseEntity.noContent().build();
    }
}
