package org.casa.backend.dto;

import java.time.LocalDateTime;

import org.casa.backend.enums.EstadoPost;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostulacionDto {
    private String idPostulacion;
    private String idUsuario;
    private String idActividad;
    private String postulante;
    private String motivo;
    private EstadoPost estadoPos;
    private LocalDateTime fechaPostulacion;
}
