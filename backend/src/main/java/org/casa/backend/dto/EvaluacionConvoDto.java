package org.casa.backend.dto;

import java.time.LocalDateTime;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EvaluacionConvoDto {
    private String idEvaluacion;
    private String idJurado;
    private String idPostulacion;
    private Integer ronda;
    private boolean semifinalista;
    private Double calificacion;
    private String justificacion;
    private boolean finalista;
    private LocalDateTime fechaHora;
    private String idUsuario;
    private String postulante;
    private String nombre;
    private String apellidos;
    private String nombreJ;
    private String apellidosJ;
}
