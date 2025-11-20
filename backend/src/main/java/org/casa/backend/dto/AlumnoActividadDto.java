package org.casa.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AlumnoActividadDto {
    private String nombre;
    private String apellidos;
    private String idAlumno;
    private String idPostulacion;
    private String tituloActividad;
}
