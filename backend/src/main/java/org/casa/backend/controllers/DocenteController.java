package org.casa.backend.controllers;

import java.util.List;

import org.casa.backend.dto.DocenteDto;
import org.casa.backend.service.DocenteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;


@AllArgsConstructor
@RestController
@RequestMapping("/api/docentes")
@CrossOrigin(origins = "*")
public class DocenteController {

    private DocenteService docenteService;

    @PostMapping
    public ResponseEntity<DocenteDto> createDocente(@RequestBody DocenteDto docenteDto) {
        DocenteDto savedDocente = docenteService.createDocente(docenteDto);
        return new ResponseEntity<>(savedDocente, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocenteDto> getDocenteById(@PathVariable("id") String idDocente) {
        DocenteDto docenteDto = docenteService.getDocenteById(idDocente);
        return ResponseEntity.ok(docenteDto);
    }

    @GetMapping
    public ResponseEntity<List<DocenteDto>> getAllDocentes() {
        List<DocenteDto> docentes = docenteService.getAllDocentes();
        return ResponseEntity.ok(docentes);
    }


}
