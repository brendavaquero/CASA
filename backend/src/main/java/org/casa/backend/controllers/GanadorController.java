package org.casa.backend.controllers;

import java.util.List;

import org.casa.backend.dto.FinalistaDto;
import org.casa.backend.dto.GanadorDto;
import org.casa.backend.dto.request.SeleccionarGanadorRequest;
import org.casa.backend.entity.Ganador;
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
@CrossOrigin("*")
public class GanadorController {
    private GanadorService ganadorService;

    @PostMapping
    public ResponseEntity<GanadorDto> create(@RequestBody GanadorDto dto){
        return new ResponseEntity<>(ganadorService.createGanador(dto),HttpStatus.CREATED);
    }

//    @PostMapping("/confirmar")
//    public ResponseEntity<Void> confirmarGanador(
//            @RequestBody FinalistaDto finalistaDto
//    ) {
//        System.out.println(">>> CONTROLLER confirmarGanador");
//        System.out.println("Resultado: " + finalistaDto.getIdResultado());
//        System.out.println("Postulación: " + finalistaDto.getIdPostulacion());
//        ganadorService.crearGanadorDesdeFinalista(finalistaDto);
//        return ResponseEntity.ok().build();
//    }

    @PostMapping("/confirmar")
    public ResponseEntity<Void> seleccionarGanador(
            @RequestBody SeleccionarGanadorRequest request
    ) {
        ganadorService.seleccionarGanador(request.getIdResultado());
        return ResponseEntity.ok().build();
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
