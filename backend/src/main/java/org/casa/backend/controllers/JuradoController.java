package org.casa.backend.controllers;

import java.util.List;

import org.casa.backend.dto.JuradoDto;
import org.casa.backend.service.JuradoService;
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

@RestController
@RequestMapping("/api/jurado")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class JuradoController {
    private JuradoService juradoService;

    @GetMapping("/{id}")
    public ResponseEntity<JuradoDto> getJuradoById(@PathVariable("id") String idJurado){
        JuradoDto juradoDto = juradoService.getJuradoById(idJurado);
        return ResponseEntity.ok(juradoDto);
    }

    @GetMapping
    public ResponseEntity<List<JuradoDto>> getAll(){
        return ResponseEntity.ok(juradoService.getAllJurados());
    }
/*
    @PostMapping
    public ResponseEntity<JuradoDto> crear(@RequestBody JuradoDto dto) {
        return new ResponseEntity<>(juradoService.crear(dto), HttpStatus.CREATED);
    }

    @GetMapping("/convocatoria/{id}")
    public List<JuradoDto> listar(@PathVariable String id) {
        return juradoService.listarPorConvocatoria(id);
    }*/
}
