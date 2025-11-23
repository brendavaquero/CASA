package org.casa.backend.service;

import org.casa.backend.dto.ArchivoDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ArchivoService {
    ArchivoDto createArchivo(ArchivoDto archivoDto);
    ArchivoDto getArchivoById(String idArchivo);
    void deleteArchivo(String idAlumno);
    List<ArchivoDto> getAllArchivos();
    ArchivoDto uploadArchivo(MultipartFile file, String idActividad, String idPostulacion);
    List<ArchivoDto> getArchivosByActividad(String idActividad);
    List<ArchivoDto> getEvidenciasByActividad(String idActividad);
    byte[] getZipEvidenciasPrograma(String idPrograma) throws IOException;
}
