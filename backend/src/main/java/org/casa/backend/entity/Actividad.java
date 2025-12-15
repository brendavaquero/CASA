package org.casa.backend.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.casa.backend.enums.EstadoActividad;
import org.casa.backend.enums.TipoActividad;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity

@Table(name = "actividad")
@Inheritance(strategy = InheritanceType.JOINED) // herencia JOINED

public abstract class Actividad {
    @Column(name = "num", insertable = false, updatable = false)
    private Integer num;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_actividad", insertable = false, updatable = false)
    private String idActividad;

    @Size(max = 200)
    @NotNull
    @Column(name = "titulo", nullable = false, length = 200)
    private String titulo;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    /*@Size(max = 100)
    @Column(name = "tipo", length = 100)
    private String tipo;*/

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false, length = 20)
    private TipoActividad tipo;

    @Column(name = "fecha_inicio")
    private LocalDate fechaInicio;

    @Column(name = "fecha_cierre")
    private LocalDate fechaCierre;

    @Column(name = "fecha_resultados")
    private LocalDate fechaResultados;

    @Column(name = "fecha_creacion", insertable = false, updatable = false)
    private Instant fechaCreacion;

    @Column(name = "requisitos", columnDefinition = "TEXT")
    private String requisitos;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false, length = 20)
    private EstadoActividad estado;

    @Column(name = "imagen", nullable = false, length = 300)
    private String imagen;

    @Column(name = "requiereMuestraTrabajo", nullable = false)
    private boolean requiereMuestraTrabajo = false;

    @Column(name = "visible", nullable = false)
    private boolean visible = true;

    public Actividad(String idActividad, String titulo, String descripcion, TipoActividad tipo, LocalDate fechaInicio, LocalDate fechaCierre, LocalDate fechaResultados,  Instant fechaCreacion, String requisitos, EstadoActividad estado, String imagen, boolean requiereMuestraTrabajo, boolean visible) {
        this.idActividad = idActividad;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.fechaInicio = fechaInicio;
        this.fechaCierre = fechaCierre;
        this.fechaResultados = fechaResultados;
        this.fechaCreacion = fechaCreacion;
        this.requisitos = requisitos;
        this.estado = estado;
        this.imagen = imagen;
        this.requiereMuestraTrabajo = requiereMuestraTrabajo;
        this.visible = visible;
    }
    @OneToMany(mappedBy = "actividad", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Archivo> archivos = new ArrayList<>();
}
