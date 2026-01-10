package org.casa.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.casa.backend.entity.Archivo;
import org.casa.backend.enums.TipoArchivo;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostulacionPendienteJuradoDto {

    private String idPostulacion;
    private String nombre;
    private String apellidos;
    private String postulante;
    private boolean infantil;
    private String nombreObra;
    private TipoArchivo tipo;
}
