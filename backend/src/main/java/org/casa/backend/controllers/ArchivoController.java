package org.casa.backend.controllers;

import lombok.AllArgsConstructor;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.casa.backend.dto.ArchivoDto;
import org.casa.backend.entity.Archivo;
import org.casa.backend.enums.CategoriaArchivo;
import org.casa.backend.service.ArchivoService;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
            @RequestParam(required = false) String idPostulacion,
            @RequestParam(required = false) CategoriaArchivo categoria
    ) {
        return ResponseEntity.ok(archivoService.uploadArchivo(file, idActividad, idPostulacion, categoria));
    }
    @GetMapping("/actividad/{idActividad}")
    public ResponseEntity<List<ArchivoDto>> getArchivosActividad(@PathVariable String idActividad) {
        return ResponseEntity.ok(archivoService.getArchivosByActividad(idActividad));
    }

    @GetMapping("/{idActividad}/evidencias")
    public ResponseEntity<List<ArchivoDto>> getEvidenciasByActividad(@PathVariable String idActividad) {
        return ResponseEntity.ok(archivoService.getEvidenciasByActividad(idActividad));
    }
    @DeleteMapping("/archivo/{id}")
    public ResponseEntity<String> deleteArchivo(@PathVariable String id) {
        archivoService.deleteArchivoFisicoYRegistro(id);
        return ResponseEntity.ok("Archivo eliminado correctamente.");
    }

    // archivo con ruta completa
    /*@GetMapping("/{idPostulacion}")
    public ResponseEntity<Resource> verArchivo(@PathVariable Long idPostulacion) {

        Archivo archivo = archivoService.obtenerPorPostulacion(idPostulacion);

        Path path = Paths.get(archivo.getRuta());
        Resource resource = new FileSystemResource(path);

        MediaType mediaType = archivo.getTipo().equalsIgnoreCase("PDF")
                ? MediaType.APPLICATION_PDF
                : MediaType.APPLICATION_OCTET_STREAM;

        return ResponseEntity.ok()
                .contentType(mediaType)
                .body(resource);
    }*/

    @GetMapping("/postulacion/{idPostulacion}")
    public ResponseEntity<Resource> verArchivo(@PathVariable String idPostulacion) {

        Archivo archivo = archivoService.obtenerPorPostulacion(idPostulacion);

        Path path = Paths.get(archivo.getRuta());

        System.out.println("Ruta guardada en BD: " + archivo.getRuta());
        System.out.println("Ruta absoluta resuelta: " + path.toAbsolutePath());
        System.out.println("Existe archivo?: " + Files.exists(path));

        Resource resource = new FileSystemResource(path);

        MediaType mediaType;
        switch (archivo.getTipo()) {
            case DOCUMENTO:
                mediaType = MediaType.APPLICATION_PDF;
                break;
            case AUDIO:
                mediaType = MediaType.APPLICATION_OCTET_STREAM;
                break;
            default:
                mediaType = MediaType.APPLICATION_OCTET_STREAM;
        }

        return ResponseEntity.ok()
                .contentType(mediaType)
                .body(resource);
    }
}
