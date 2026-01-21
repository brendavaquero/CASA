package org.casa.backend.controllers;

import lombok.AllArgsConstructor;
import org.casa.backend.dto.ReporteTrimestralDTO;
import org.casa.backend.service.ReporteTrimestralService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/reportes")
public class ReporteController {

    private ReporteTrimestralService reporteTrimestralService;

    private final ReporteTrimestralService reporteService;

    @GetMapping("/trimestral")
    public ReporteTrimestralDTO obtenerReporte(
            @RequestParam int anio,
            @RequestParam int trimestre
    ) {
        return reporteService.generarReporteTrimestral(anio, trimestre);
    }
}
