package org.casa.backend.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "actividad")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Actividad { 

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_actividad", length = 50, insertable = false, updatable = false)
    private String idActividad;

    @Column(name = "num", insertable = false, updatable = false)
    private Integer num;

    @Column(name = "titulo", length = 200, nullable = false)
    private String titulo;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "fecha_inicio")
    private LocalDate fechaInicio;

    @Column(name = "fecha_cierre")
    private LocalDate fechaCierre;

    @Column(name = "fecha_resultados")
    private LocalDate fechaResultados;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "requisitos", columnDefinition = "TEXT")
    private String requisitos;

    @Column(name = "estado", length = 50)
    private String estado;

    @Column(name = "tipo_actividad",length=31)
    private String tipoActividad;

    @PrePersist
    protected void onCreate() {
        if (fechaCreacion == null) {
            fechaCreacion = LocalDateTime.now();
        }
    }
    public Actividad() {
        
    }

    public Actividad(String titulo, String descripcion, LocalDate fechaInicio, LocalDate fechaCierre, LocalDate fechaResultados, LocalDateTime fechaCreacion, String requisitos, String estado,String tipoActividad ) {
        this.titulo=titulo;
        this.descripcion=descripcion;
        this.fechaInicio=fechaInicio;
        this.fechaCierre=fechaCierre;
        this.fechaResultados=fechaResultados;
        this.fechaCreacion = fechaCreacion;
        this.requisitos = requisitos;
        this.estado = estado;
        this.tipoActividad = tipoActividad;
    }
}
