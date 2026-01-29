package org.casa.backend.controllers;

import lombok.RequiredArgsConstructor;
import org.casa.backend.dto.FinalistaDto;
import org.casa.backend.entity.ResultadoRondaUno;
import org.casa.backend.service.ResultadoRondaUnoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/ronda")
@RequiredArgsConstructor
public class ResultadoRondaUnoController {

    private final ResultadoRondaUnoService resultadoService;

    @PostMapping("/uno/cerrar/{idConvocatoria}")
    public ResponseEntity<Void> cerrarRondaUno(
            @PathVariable String idConvocatoria
    ) {
        resultadoService.cerrarRondaUno(idConvocatoria);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/uno/resultados/{idConvocatoria}")
    public ResponseEntity<List<ResultadoRondaUno>> obtenerResultados(
            @PathVariable String idConvocatoria
    ) {
        return ResponseEntity.ok(
                resultadoService.obtenerResultados(idConvocatoria)
        );
    }

    /*@GetMapping("/finalistas/{idConvocatoria}")
    public ResponseEntity<List<ResultadoRondaUno>> obtenerFinalistas(
            @PathVariable String idConvocatoria
    ) {
        return ResponseEntity.ok(
                resultadoService.obtenerFinalistas(idConvocatoria)
        );
    }*/

    @GetMapping("/uno/finalistas/{idConvocatoria}")
    public ResponseEntity<List<FinalistaDto>> obtenerFinalistas(
            @PathVariable String idConvocatoria
    ) {
        return ResponseEntity.ok(
                resultadoService.obtenerFinalistasDTO(idConvocatoria)
        );
    }

    @GetMapping("/final/{id}")
    public ResponseEntity<List<FinalistaDto>> entrarRondaFinal(
            @PathVariable String id) {

        return ResponseEntity.ok(
                resultadoService.prepararRondaFinal(id)
        );
    }
}
