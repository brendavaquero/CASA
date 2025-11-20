package org.casa.backend.controllers;

import lombok.AllArgsConstructor;

import java.util.List;

import org.casa.backend.dto.ArchivoDto;
import org.casa.backend.service.ArchivoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@AllArgsConstructor
@RestController
@RequestMapping("/api/archivos")
@CrossOrigin(origins = "http://localhost:3000")
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarAlumno(@PathVariable("id") String idArchivo) {
        archivoService.deleteArchivo(idArchivo);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/upload")
    public ResponseEntity<ArchivoDto> upload(
            @RequestParam("file") MultipartFile file,
            @RequestParam(required = false) String idActividad,
            @RequestParam(required = false) String idPostulacion
    ) {
        return ResponseEntity.ok(archivoService.uploadArchivo(file, idActividad, idPostulacion));
    }
    @GetMapping("/actividad/{idActividad}")
    public ResponseEntity<List<ArchivoDto>> getArchivosActividad(@PathVariable String idActividad) {
        return ResponseEntity.ok(archivoService.getArchivosByActividad(idActividad));
    }
}
