package org.casa.backend.controllers;

import java.io.IOException;
import java.util.List;

import org.casa.backend.dto.ProgramaDto;
import org.casa.backend.service.ArchivoService;
import org.casa.backend.service.ProgramaService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/programas")
public class ProgramaController {
    private ProgramaService programaService;
    private ArchivoService archivoService;

    @GetMapping
    public ResponseEntity<List<ProgramaDto>> getAllProgramas() {
        List<ProgramaDto> programas = programaService.getAllProgramas();
        return ResponseEntity.ok(programas);
    }

    @GetMapping("/{id}")
    public ProgramaDto getById(@PathVariable String id) {
        return programaService.getById(id);
    }

    @PostMapping
    public ProgramaDto create(@RequestBody ProgramaDto dto) {
        return programaService.createPrograma(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProgramaDto> updatePrograma(@PathVariable("id") String programaId, @RequestBody ProgramaDto updatedPrograma){
        ProgramaDto programa = programaService.updatePrograma(programaId, updatedPrograma);
        return ResponseEntity.ok(programa);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        programaService.deletePrograma(id);
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<ProgramaDto> getProgramasByUsuario(@PathVariable String usuarioId) {
        return programaService.getProgramasByUsuario(usuarioId);
    }

    @GetMapping("/{idPrograma}/evidencias/zip")
    public ResponseEntity<byte[]> descargarZipEvidencias(@PathVariable String idPrograma) throws IOException {

        byte[] zipBytes = archivoService.getZipEvidenciasPrograma(idPrograma);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=evidencias_programa_" + idPrograma + ".zip")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(zipBytes);
    }
}