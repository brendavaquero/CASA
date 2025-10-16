package org.casa.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ArchivoDto {
    private String idArchivo;
    private String nombre;
    private String ruta;
    private String tipo;
    private Instant fecha;

    /*public ArchivoDto(String nombre, String ruta, String tipo, Instant fecha) {
        this.idArchivo = idArchivo;
        this.nombre = nombre;
        this.ruta = ruta;
        this.tipo = tipo;
        this.fecha = fecha;
    }*/

}