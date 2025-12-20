package org.casa.backend.controllers;

import java.util.List;

import org.casa.backend.dto.GanadorDto;
import org.casa.backend.service.GanadorService;
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
@RequestMapping("/api/ganador")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class GanadorController {
    private GanadorService ganadorService;

    @PostMapping
    public ResponseEntity<GanadorDto> create(@RequestBody GanadorDto dto){
        return new ResponseEntity<>(ganadorService.createGanador(dto),HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<GanadorDto>> getAll(){
        return ResponseEntity.ok(ganadorService.getAllGanadores());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GanadorDto> getGanadorById(@PathVariable("id") String idGanador){
        GanadorDto ganadorDto = ganadorService.getGanadorById(idGanador);
        return ResponseEntity.ok(ganadorDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GanadorDto> actualizarGanador(@PathVariable("id") String idGanador, @RequestBody GanadorDto updatedGanador){
        GanadorDto ganadorDto = ganadorService.updateGanador(idGanador, updatedGanador);
        return ResponseEntity.ok(ganadorDto);
    }

}
