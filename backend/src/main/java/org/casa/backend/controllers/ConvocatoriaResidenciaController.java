package org.casa.backend.controllers;

import java.util.List;

import org.casa.backend.dto.ConvocatoriaResidenciaDto;
import org.casa.backend.service.ConvocatoriaResidenciaService;
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

@AllArgsConstructor
@RestController
@RequestMapping("/api/convocatoriasyresidencias")
@CrossOrigin(origins = "*")
public class ConvocatoriaResidenciaController {
    private ConvocatoriaResidenciaService convocatoriaResidenciaService;

    @PostMapping
    public ResponseEntity<ConvocatoriaResidenciaDto> createConvocatoriaResi(@RequestBody ConvocatoriaResidenciaDto convocatoriaResiDto){
        ConvocatoriaResidenciaDto savedConvocatoriaResi = convocatoriaResidenciaService.createConvocatoriaResi(convocatoriaResiDto);
        return new ResponseEntity<>(savedConvocatoriaResi,HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConvocatoriaResidenciaDto> getConvocatoriaResiById(@PathVariable("id") String idActividad){
        ConvocatoriaResidenciaDto convocatoriaResiDto = convocatoriaResidenciaService.getConvocatoriaResiById(idActividad);
        return ResponseEntity.ok(convocatoriaResiDto);
    }

    @GetMapping
    public ResponseEntity<List<ConvocatoriaResidenciaDto>> getAllConvocatoriasResis(){
        List<ConvocatoriaResidenciaDto> convocatoriasResis = convocatoriaResidenciaService.getAllConvocatoriaResi();
        return ResponseEntity.ok(convocatoriasResis);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConvocatoriaResidenciaDto> updateConvocatoriaResi(@PathVariable("id") String convoResiId,@RequestBody ConvocatoriaResidenciaDto updatedConvoResi){
        ConvocatoriaResidenciaDto convoResiDto = convocatoriaResidenciaService.updateConvocatoriaResi(convoResiId, updatedConvoResi);
        return ResponseEntity.ok(convoResiDto);
    }
}
