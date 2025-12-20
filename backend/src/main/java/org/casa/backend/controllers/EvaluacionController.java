package org.casa.backend.controllers;

import java.util.List;

import org.casa.backend.dto.EvaluacionDto;
import org.casa.backend.service.EvaluacionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/evaluacion")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class EvaluacionController {
    private EvaluacionService evaluacionService;

    @PostMapping
    public ResponseEntity<EvaluacionDto> create(@RequestBody EvaluacionDto dto){
        return new ResponseEntity<>(evaluacionService.createEvaluacion(dto),HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EvaluacionDto> getEvaluacionById(@PathVariable("id") String idEvaluacion){
        EvaluacionDto evaluacionDto = evaluacionService.getEvaluacionById(idEvaluacion);
        return ResponseEntity.ok(evaluacionDto);
    }

    @GetMapping
    public ResponseEntity<List<EvaluacionDto>> getAll(){
        return ResponseEntity.ok(evaluacionService.getAllEvaluaciones());
    }

    @PutMapping("/{id}")
    public ResponseEntity<EvaluacionDto> actualizarEvaluacion(@PathVariable("id") String idEvaluacion, @RequestBody EvaluacionDto updatedEvaluacion){
        EvaluacionDto evaluacionDto = evaluacionService.updateEvaluacion(idEvaluacion,updatedEvaluacion);
        return ResponseEntity.ok(evaluacionDto);
    }
}
