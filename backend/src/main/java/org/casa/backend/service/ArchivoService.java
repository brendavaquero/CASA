package org.casa.backend.service;

import org.casa.backend.dto.ArchivoDto;

import java.util.List;

public interface ArchivoService {
    ArchivoDto createArchivo(ArchivoDto archivoDto);
    ArchivoDto getArchivoById(String idArchivo);
    void deleteArchivo(String idAlumno);
    List<ArchivoDto> getAllArchivos();
}
