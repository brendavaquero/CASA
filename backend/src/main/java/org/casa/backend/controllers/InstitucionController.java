package org.casa.backend.controllers;
import java.util.List;

import org.casa.backend.dto.InstitucionDTO;
import org.casa.backend.service.InstitucionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/instituciones")
@AllArgsConstructor
public class InstitucionController {

    private InstitucionService institucionService;

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<InstitucionDTO> createInstitucion(
            @RequestPart("institucion") InstitucionDTO dto,
            @RequestPart(value = "logo", required = false) MultipartFile logo
    ) {
        InstitucionDTO created = institucionService.createInstitucion(dto, logo);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InstitucionDTO> getInstitucionById(@PathVariable Long id) {
        return ResponseEntity.ok(institucionService.getInstitucionById(id));
    }

    @GetMapping
    public ResponseEntity<List<InstitucionDTO>> getAllInstituciones() {
        return ResponseEntity.ok(institucionService.getAllInstituciones());
    }

    @PutMapping(value = "/{id}", consumes = "multipart/form-data")
    public ResponseEntity<InstitucionDTO> updateInstitucion(
            @PathVariable Long id,
            @RequestPart("institucion") InstitucionDTO dto,
            @RequestPart(value = "logo", required = false) MultipartFile logo
    ) {
        InstitucionDTO updated = institucionService.updateInstitucion(id, dto, logo);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInstitucion(@PathVariable Long id) {
        institucionService.deleteInstitucion(id);
        return ResponseEntity.noContent().build();
    }
}