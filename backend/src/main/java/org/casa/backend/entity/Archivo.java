package org.casa.backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.casa.backend.enums.TipoArchivo;
import org.casa.backend.enums.CategoriaArchivo;

import java.time.Instant;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "archivo")
public class Archivo {
    @Column(name = "num", insertable = false, updatable = false)
    private Integer num;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_archivo", insertable = false, updatable = false)
    private String idArchivo;

    @Size(max = 200)
    @NotNull
    @Column(name = "nombre", nullable = false, length = 200)
    private String nombre;

    @Size(max = 255)
    @NotNull
    @Column(name = "ruta", nullable = false)
    private String ruta;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false, length = 20)
    private TipoArchivo tipo;

    @Column(name = "fecha", insertable = false, updatable = false)
    private Instant fecha;

    @ManyToOne
    @JoinColumn(name = "id_actividad")
    private Actividad actividad;

    @ManyToOne
    @JoinColumn(name = "id_postulacion")
    private Postulacion postulacion;

    @Enumerated(EnumType.STRING)
    @Column(name = "categoria", nullable = false, length = 20)
    private CategoriaArchivo categoria;

    //quitar
    public Archivo(String idArchivo,String nombre, String ruta, TipoArchivo tipo, Instant fecha, CategoriaArchivo categoria) {
        this.idArchivo = idArchivo;
        this.nombre = nombre;
        this.ruta = ruta;
        this.tipo = tipo;
        this.fecha = fecha;
        this.categoria = categoria;
    }


}