package org.casa.backend.controllers;

import java.util.List;

import org.casa.backend.dto.ProgramaDto;
import org.casa.backend.service.ProgramaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/programas")
public class ProgramaController {
    private ProgramaService programaService;

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
}