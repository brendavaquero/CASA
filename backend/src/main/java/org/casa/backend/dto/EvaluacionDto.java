package org.casa.backend.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EvaluacionDto {
    private String idEvaluacion;

    private String idJurado;
    private String idPostulacion;

    private Integer ronda;
    private boolean semifinalista;
    private Double calificacion;
    private String justificacion;
    private boolean finalista;
    private LocalDateTime fechaHora;
}
