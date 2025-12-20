package org.casa.backend.controllers;

import java.util.List;

import org.casa.backend.dto.ObraDto;
import org.casa.backend.service.ObraService;
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
@RequestMapping("/api/obra")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class ObraController {
    private ObraService obraService;

    @GetMapping
    public ResponseEntity<List<ObraDto>> getAll(){
        return ResponseEntity.ok(obraService.getAllObras());
    }

    @PostMapping
    public ResponseEntity<ObraDto> crear(@RequestBody ObraDto obraDto){
        return new ResponseEntity<>(obraService.crear(obraDto),HttpStatus.CREATED);

    }

    @GetMapping("/{id}")
    public ResponseEntity<ObraDto> getObraById(@PathVariable("id") String idObra){
        ObraDto obraDto = obraService.getObraById(idObra);
        return ResponseEntity.ok(obraDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ObraDto> updateObra(@PathVariable("id") String idObra, @RequestBody ObraDto updateObraDto){
        ObraDto obraDto = obraService.updateObra(idObra, updateObraDto);
        return ResponseEntity.ok(obraDto);
    }
}
