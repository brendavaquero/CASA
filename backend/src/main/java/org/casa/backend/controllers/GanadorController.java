package org.casa.backend.controllers;

import java.util.List;

import org.casa.backend.dto.FinalistaDto;
import org.casa.backend.dto.GanadorConvocatoriaDto;
import org.casa.backend.dto.GanadorDto;
import org.casa.backend.dto.request.SeleccionarGanadorRequest;
import org.casa.backend.entity.Ganador;
import org.casa.backend.service.GanadorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

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

    @PostMapping("/uploadImagen")
    public ResponseEntity<String> uploadImagen(
            @RequestParam("file") MultipartFile file,
            @RequestParam("idGanador") String idGanador
    ) {
        String url = ganadorService.uploadImagen(file, idGanador);
        return ResponseEntity.ok(url);
    }
    @GetMapping("/convocatoria/{idConvocatoria}")
    public ResponseEntity<List<GanadorConvocatoriaDto>> obtenerGanadoresPorConvocatoria(
            @PathVariable String idConvocatoria) {

        return ResponseEntity.ok(
                ganadorService.obtenerGanadoresPorConvocatoria(idConvocatoria)
        );
    }
}
