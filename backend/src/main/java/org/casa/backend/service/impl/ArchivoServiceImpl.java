package org.casa.backend.service.impl;

import lombok.AllArgsConstructor;
import org.casa.backend.dto.ArchivoDto;
import org.casa.backend.entity.Archivo;
import org.casa.backend.exception.ResourceNotFoundException;
import org.casa.backend.mapper.ArchivoMapper;
import org.casa.backend.repository.ArchivoRepositorio;
import org.casa.backend.service.ArchivoService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ArchivoServiceImpl implements ArchivoService {
    private ArchivoRepositorio archivoRepositorio;
    @Override
    public ArchivoDto createArchivo(ArchivoDto archivoDto) {
        Archivo archivo = ArchivoMapper.mapToArchivo(archivoDto);
        Archivo savedArchivo = archivoRepositorio.save(archivo);

        return null;
    }

    @Override
    public ArchivoDto getArchivoById(String idArchivo) {
        Archivo archivo = archivoRepositorio.findById(idArchivo)
                .orElseThrow(() -> new ResourceNotFoundException("Archivo no encontrado. ID: " + idArchivo));
        return ArchivoMapper.mapToArchivoDto(archivo);
    }
}
