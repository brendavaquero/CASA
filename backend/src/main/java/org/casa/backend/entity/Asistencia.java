package org.casa.backend.entity;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "asistencia")
public class Asistencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_asistencia", length = 50, insertable = false, updatable = false)
    private String idAsistencia;

    @Column(name = "num", insertable = false, updatable = false)
    private Integer num;

    @ManyToOne
    @JoinColumn(name = "id_alumno", nullable = false)
    private Alumno alumno;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "presente", nullable = false)
    private Boolean presente = false;

    public Asistencia() {}

    public Asistencia(String idAsistencia, Alumno alumno, LocalDate fecha, Boolean presente) {
        this.idAsistencia = idAsistencia;
        this.alumno = alumno;
        this.fecha = fecha;
        this.presente = presente;
    }
}
