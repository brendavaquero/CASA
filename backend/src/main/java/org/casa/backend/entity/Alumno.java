package org.casa.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "alumno")
public class Alumno {

    @Id
    @Column(name = "id_alumno", length = 50)
    private String idAlumno;

    @Column(name = "num", insertable = false, updatable = false)
    private Integer num;

    @ManyToOne
    @JoinColumn(name = "id_postulacion", nullable = false)
    private Postulacion postulacion;

    @Column(name = "constancia")
    private Boolean constancia = true;

    public Alumno() {}

    public Alumno(String idAlumno, Postulacion postulacion, Boolean constancia) {
        this.idAlumno = idAlumno;
        this.postulacion = postulacion;
        this.constancia = constancia;
    }
}
