package org.casa.backend.service.impl;

import lombok.AllArgsConstructor;
import org.casa.backend.dto.ArchivoDto;
import org.casa.backend.entity.Archivo;
import org.casa.backend.entity.Usuario;
import org.casa.backend.exception.ResourceNotFoundException;
import org.casa.backend.mapper.ArchivoMapper;
import org.casa.backend.mapper.UsuarioMapper;
import org.casa.backend.repository.ArchivoRepository;
import org.casa.backend.service.ArchivoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ArchivoServiceImpl implements ArchivoService {
    private ArchivoRepository archivoRepository;
    @Override
    public ArchivoDto createArchivo(ArchivoDto archivoDto) {
        Archivo archivo = ArchivoMapper.mapToArchivo(archivoDto);
        Archivo savedArchivo = archivoRepository.save(archivo);

        return null;
    }

    @Override
    public ArchivoDto getArchivoById(String idArchivo) {
        Archivo archivo = archivoRepository.findById(idArchivo)
                .orElseThrow(() -> new ResourceNotFoundException("Archivo no encontrado. ID: " + idArchivo));
        return ArchivoMapper.mapToArchivoDto(archivo);
    }

    @Override
    public List<ArchivoDto> getAllArchivos() {
        List<Archivo> archivos =  archivoRepository.findAll();
        return archivos.stream().map((archivo) -> ArchivoMapper.mapToArchivoDto(archivo))
                .collect(Collectors.toList());
    }
}
