package org.casa.backend.controllers;

import lombok.RequiredArgsConstructor;
import org.casa.backend.dto.ReporteTrimestralDTO;
import org.casa.backend.service.ReporteTrimestralService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/reportes")
@RequiredArgsConstructor

public class ReporteController {

    private final ReporteTrimestralService reporteService;

    @GetMapping("/trimestral")
    public ResponseEntity<ReporteTrimestralDTO> getReporteTrimestral(
            @RequestParam int year,
            @RequestParam int trimestre) {

        return ResponseEntity.ok(
                reporteService.generarReporte(year, trimestre)
        );
    }
}
