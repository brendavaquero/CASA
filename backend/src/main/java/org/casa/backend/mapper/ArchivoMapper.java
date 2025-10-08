package org.casa.backend.mapper;

import org.casa.backend.dto.ArchivoDto;
import org.casa.backend.entity.Archivo;

public class ArchivoMapper {
    public static ArchivoDto mapToArchivoDto(Archivo archivo) {
        return new ArchivoDto(
                archivo.getNombre(),
                archivo.getRuta(),
                archivo.getTipo(),
                archivo.getFecha()
        );
    }

    public static Archivo mapToArchivo(ArchivoDto archivoDto) {
        return new Archivo(
                archivoDto.getNombre(),
                archivoDto.getRuta(),
                archivoDto.getTipo(),
                archivoDto.getFecha()
        );
    }
}
