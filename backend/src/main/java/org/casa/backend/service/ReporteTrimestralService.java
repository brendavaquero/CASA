package org.casa.backend.service;

import org.casa.backend.dto.ReporteTrimestralDTO;

import java.time.LocalDate;

public interface ReporteTrimestralService {

    /*ReporteTrimestralDTO generarReporteTrimestral(
            LocalDate inicio,
            LocalDate fin
    );*/
    ReporteTrimestralDTO generarReporte(int year, int trimestre);
}
