package org.casa.backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @Size(max = 50)
    @Column(name = "tipo", length = 50)
    private String tipo;

    @Column(name = "fecha", insertable = false, updatable = false)
    private Instant fecha;

    public Archivo(String idArchivo,String nombre, String ruta, String tipo, Instant fecha) {
        this.idArchivo = idArchivo;
        this.nombre = nombre;
        this.ruta = ruta;
        this.tipo = tipo;
        this.fecha = fecha;
    }
}