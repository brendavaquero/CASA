package org.casa.backend.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "evaluacion")
public class Evaluacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_evaluacion", length = 20, insertable = false, updatable = false)
    private String idEvaluacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_jurado", nullable = false)
    private Jurado jurado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_postulacion", nullable = false)
    private Postulacion postulacion;

    @Column(name = "ronda", nullable = false)
    private Integer ronda;

    @Column(name = "semifinalista")
    private boolean semifinalista = false;

    @Column(name = "calificacion")
    private Double calificacion;

    @Column(name = "justificacion", columnDefinition = "TEXT")
    private String justificacion;

    @Column(name = "finalista")
    private boolean finalista = false;

    @Column(name = "fecha_hora")
    private LocalDateTime  fechaHora = LocalDateTime.now(); 
}
