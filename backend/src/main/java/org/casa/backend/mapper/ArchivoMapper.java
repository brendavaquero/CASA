package org.casa.backend.mapper;

import org.casa.backend.dto.ArchivoDto;
import org.casa.backend.entity.Actividad;
import org.casa.backend.entity.Archivo;
import org.casa.backend.entity.Postulacion;

public class ArchivoMapper {

    public static ArchivoDto mapToArchivoDto(Archivo archivo) {
        ArchivoDto dto = new ArchivoDto();
        dto.setIdArchivo(archivo.getIdArchivo());
        dto.setNombre(archivo.getNombre());
        dto.setRuta(archivo.getRuta());
        dto.setTipo(archivo.getTipo());
        dto.setFecha(archivo.getFecha());

        if (archivo.getActividad() != null) {
            dto.setIdActividad(archivo.getActividad().getIdActividad());
        }

        if (archivo.getPostulacion() != null) {
            dto.setIdPostulacion(archivo.getPostulacion().getIdPostulacion());
        }

        return dto;
    }

    public static Archivo mapToArchivo(ArchivoDto archivoDto) {
        Archivo archivo = new Archivo();
        archivo.setIdArchivo(archivoDto.getIdArchivo());
        archivo.setNombre(archivoDto.getNombre());
        archivo.setRuta(archivoDto.getRuta());
        archivo.setTipo(archivoDto.getTipo());
        archivo.setFecha(archivoDto.getFecha());

        if (archivoDto.getIdActividad() != null) {
            Actividad actividadRef = new Actividad() {}; // clase anónima por ser abstracta
            actividadRef.setIdActividad(archivoDto.getIdActividad());
            archivo.setActividad(actividadRef);
        }

        if (archivoDto.getIdPostulacion() != null) {
            Postulacion postulacionRef = new Postulacion();
            postulacionRef.setIdPostulacion(archivoDto.getIdPostulacion());
            archivo.setPostulacion(postulacionRef);
        }
        return archivo;
    }
}
