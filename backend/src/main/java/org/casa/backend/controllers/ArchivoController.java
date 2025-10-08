package org.casa.backend.controllers;

import lombok.AllArgsConstructor;
import org.casa.backend.dto.ArchivoDto;
import org.casa.backend.service.ArchivoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/archivos")
public class ArchivoController {
    private ArchivoService archivoService;

    //Rest API ADD
    @PostMapping
    public ResponseEntity<ArchivoDto> createArchivo(@RequestBody ArchivoDto archivoDto) {
        ArchivoDto savedArchivo = archivoService.createArchivo(archivoDto);
        return new ResponseEntity<>(savedArchivo, HttpStatus.CREATED);
    }

    //Rest API GET
    @GetMapping("{id}")
    public ResponseEntity<ArchivoDto> getArchivoById(@PathVariable("id") String idArchivo) {
        ArchivoDto archivoDto = archivoService.getArchivoById(idArchivo);
        return ResponseEntity.ok(archivoDto);
    }
}
