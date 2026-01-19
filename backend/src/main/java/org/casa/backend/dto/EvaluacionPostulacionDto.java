package org.casa.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.casa.backend.enums.TipoArchivo;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EvaluacionPostulacionDto {

    // Postulación
    private String idPostulacion;
    private String postulante;
    private String nombreObra;

    // Participante
    private String nombre;
    private String apellidos;
    private String seudonimo;

    // Archivo
    private TipoArchivo tipo;
    private String ruta;

    // Convocatoria
    private String idActividad;
    private boolean infantil;

}
