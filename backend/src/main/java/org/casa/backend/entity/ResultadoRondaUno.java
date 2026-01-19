package org.casa.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class ResultadoRondaUno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_resultado", length = 20, insertable = false, updatable = false)
    private String idResultado;

    @Column(name = "id_convocatoria", length = 20, nullable = false)
    private String idConvocatoria;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "id_postulacion", nullable = false)
//    private Postulacion postulacion;

    @Column(name = "id_postulacion", length = 20, nullable = false)
    private String idPostulacion;

    @Column(name = "promedio", precision = 5, scale = 2)
    private BigDecimal promedio;

    @Column(name = "total_evaluaciones")
    private Integer totalEvaluaciones;

    @Column(name = "posicion")
    private Integer posicion;

    @Column(name = "finalista", nullable = false)
    private Boolean finalista = false;

    @Column(name = "mencion_honorifica", nullable = false)
    private Boolean mencionHonorifica = false;

    @Column(name = "fecha_calculo", insertable = false, updatable = false)
    private LocalDateTime fechaCalculo;

}