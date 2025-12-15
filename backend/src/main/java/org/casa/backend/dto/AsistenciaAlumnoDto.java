package org.casa.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AsistenciaAlumnoDto {
    private String idUsuario;
    private String nombre;
    private String apellidos;
    private String idAlumno;
    private Long asistenciasPresentes;
    private Double porcentajeAsistencia;
}
