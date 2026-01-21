package org.casa.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ActaGanadorDto {

    // ===== Datos del acta =====
    private String tipo;        // "DICTAMEN"
    private LocalDate fecha;
    private String lugar;

    // ===== Convocatoria =====
    private String nombreConvocatoria;
    private String convocantes;
    private String premio;

    // ===== Ganador =====
    private String nombreGanador;
    private String nombreObra;
    private BigDecimal calificacionFinal;

    // ===== Jurado =====
    private List<String> jurados;

}
