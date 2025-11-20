package org.casa.backend.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AlumnoTallerDto {
    private String idUsuario;
    private String nombre;
    private String apellidos;
    private String idAlumno;
    private String idActividad;
    private String tituloActividad;
}
