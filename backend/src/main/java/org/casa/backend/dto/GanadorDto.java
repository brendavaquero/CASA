package org.casa.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GanadorDto {
    private String idGanador;
    private String idEvaluacion;
    private String semblanza;
    private String foto;
    private String idArchivo;
}
