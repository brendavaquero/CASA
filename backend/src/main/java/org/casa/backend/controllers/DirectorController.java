package org.casa.backend.controllers;

import java.util.List;

import org.casa.backend.dto.DirectorDto;
import org.casa.backend.service.DirectorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/directores")
@CrossOrigin("*")
public class DirectorController {
    private final DirectorService directorService;

    public DirectorController(DirectorService directorService) {
        this.directorService = directorService;
    }

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<DirectorDto> crear(
            @RequestParam String nombre,
            @RequestParam(required = false) MultipartFile firma
    ) {
        return ResponseEntity.ok(directorService.crear(nombre, firma));
    }

    @PutMapping(value = "/{id}", consumes = "multipart/form-data")
    public ResponseEntity<DirectorDto> actualizar(
            @PathVariable Long id,
            @RequestParam String nombre,
            @RequestParam(required = false) MultipartFile firma
    ) {
        return ResponseEntity.ok(directorService.actualizar(id, nombre, firma));
    }

    @GetMapping
    public ResponseEntity<List<DirectorDto>> listar() {
        return ResponseEntity.ok(directorService.listar());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        directorService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
