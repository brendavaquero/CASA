package org.casa.backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistroPostalPostulacionDto {

    // Postulación
    private String idUsuario;
    private String idActividad;
    private String nombreObra;

    // Archivos
    private List<ArchivoDto> archivos;
}

