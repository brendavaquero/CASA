package org.casa.backend.service;

import org.casa.backend.dto.ArchivoDto;

public interface ArchivoService {
    ArchivoDto createArchivo(ArchivoDto archivoDto);
    ArchivoDto getArchivoById(String idArchivo);
}
