package org.casa.backend.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AsistenciaDto {
    private String idAsistencia;
    private String idAlumno;
    private LocalDate fecha;
    private Boolean presente;
}
