package org.casa.backend.dto;

import lombok.*;
import org.casa.backend.enums.EstadoPost;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostulacionParticipanteDto {
    private String idPostulacion;
    private String idUsuario;
    private String idActividad;
    private String postulante;
    private String motivo;
    private EstadoPost estadoPos;
    private LocalDateTime fechaPostulacion;

    private ParticipanteDto participante;
}
