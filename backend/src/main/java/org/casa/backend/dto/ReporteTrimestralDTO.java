package org.casa.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReporteTrimestralDTO {

    private int anio;
    private int trimestre;

    private ResumenActividadDTO talleres;
    private ResumenActividadDTO convocatorias;
}

