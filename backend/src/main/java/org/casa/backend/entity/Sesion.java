package org.casa.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sesion")
public class Sesion {
    @Column(name = "num", insertable = false, updatable = false)
    private Integer num;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sesion", length = 20, insertable = false)
    private String idSesion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_taller_diplomado", nullable = false)
    private TallerDiplomado tallerDiplomado;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin")
    private LocalDate fechaFin;

    @Column(name = "hora_inicio", nullable = false)
    private LocalTime horaInicio;

    @Column(name = "hora_fin")
    private LocalTime horaFin;

    @Column(name = "aula")
    private String aula;

    public Sesion(String idSesion, TallerDiplomado tallerDiplomado, LocalDate fechaInicio,
                  LocalDate fechaFin, LocalTime horaInicio, LocalTime horaFin, String aula) {
        this.idSesion = idSesion;
        this.tallerDiplomado = tallerDiplomado;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.aula = aula;
    }

}