package org.casa.backend.service;

import org.casa.backend.dto.ReporteTrimestralDTO;
import org.springframework.stereotype.Service;

public interface ReporteTrimestralService {
    ReporteTrimestralDTO generarReporteTrimestral(int anio, int trimestre);
}
