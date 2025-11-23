package org.casa.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.casa.backend.enums.TipoArchivo;
import org.casa.backend.enums.CategoriaArchivo;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ArchivoDto {
    private String idArchivo;
    private String nombre;
    private String ruta;
    private TipoArchivo tipo;
    private Instant fecha;
    private String idActividad;
    private String idPostulacion;
    private CategoriaArchivo categoria;

    /*public ArchivoDto(String nombre, String ruta, String tipo, Instant fecha) {
        this.idArchivo = idArchivo;
        this.nombre = nombre;
        this.ruta = ruta;
        this.tipo = tipo;
        this.fecha = fecha;
    }*/

}