package org.casa.backend.dto;

import java.math.BigDecimal;

import org.casa.backend.entity.Ganador;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GanadorConvocatoriaDto {
    private String idGanador;
    private String semblanza;
    private String foto;

    private String idResultado;
    private String idConvocatoria;
    private String idPostulacion;
    private BigDecimal promedio;
    private Integer totalEvaluaciones;
    private Integer posicion;
    private Boolean finalista;
    private Boolean mencionHonorifica;

    private String idArchivo;

     public GanadorConvocatoriaDto(Ganador ganador) {
        this.idGanador = ganador.getIdGanador();
        this.semblanza = ganador.getSemblanza();
        this.foto = ganador.getFoto();

        if (ganador.getResultado() != null) {
            this.idResultado = ganador.getResultado().getIdResultado();
            this.idConvocatoria = ganador.getResultado().getIdConvocatoria();
            this.idPostulacion = ganador.getResultado().getIdPostulacion();
            this.promedio = ganador.getResultado().getPromedio();
            this.totalEvaluaciones = ganador.getResultado().getTotalEvaluaciones();
            this.posicion = ganador.getResultado().getPosicion();
            this.finalista = ganador.getResultado().getFinalista();
            this.mencionHonorifica = ganador.getResultado().getMencionHonorifica();
        }

        if (ganador.getArchivo() != null) {
            this.idArchivo = ganador.getArchivo().getIdArchivo();
        }
    }
}
