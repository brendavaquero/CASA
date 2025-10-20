package org.casa.backend.service.impl;

import lombok.AllArgsConstructor;
import org.casa.backend.dto.ArchivoDto;
import org.casa.backend.entity.Actividad;
import org.casa.backend.entity.Archivo;
import org.casa.backend.entity.Postulacion;
import org.casa.backend.exception.ResourceNotFoundException;
import org.casa.backend.mapper.ArchivoMapper;
import org.casa.backend.repository.ActividadRepository;
import org.casa.backend.repository.ArchivoRepository;
import org.casa.backend.repository.PostulacionRepository;
import org.casa.backend.repository.TallerDiplomadoRepository;
import org.casa.backend.service.ArchivoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ArchivoServiceImpl implements ArchivoService {
    private ArchivoRepository archivoRepository;
    private ActividadRepository actividadRepository;
    private PostulacionRepository postulacionRepository;

    /*@Override
    public ArchivoDto createArchivo(ArchivoDto archivoDto) {
        Archivo archivo = ArchivoMapper.mapToArchivo(archivoDto);
        Archivo savedArchivo = archivoRepository.save(archivo);

        return null;
    }*/

    @Override
    public ArchivoDto createArchivo(ArchivoDto archivoDto) {
        Archivo archivo = ArchivoMapper.mapToArchivo(archivoDto);

        // Validar exclusividad
        if (archivoDto.getIdActividad() != null && archivoDto.getIdPostulacion() != null) {
            throw new RuntimeException("Un archivo solo puede estar asociado a Actividad o Postulación, no a ambas");
        }

        // Asociar Actividad si existe
        if (archivoDto.getIdActividad() != null) {
            Actividad actividad = actividadRepository.findById(archivoDto.getIdActividad())
                    .orElseThrow(() -> new RuntimeException("Actividad no encontrada"));
            archivo.setActividad(actividad);
        }

        // Asociar Postulación si existe
        if (archivoDto.getIdPostulacion() != null) {
            Postulacion postulacion = postulacionRepository.findById(archivoDto.getIdPostulacion())
                    .orElseThrow(() -> new RuntimeException("Postulación no encontrada"));
            archivo.setPostulacion(postulacion);
        }

        Archivo savedArchivo = archivoRepository.save(archivo);

        return ArchivoMapper.mapToArchivoDto(savedArchivo);
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
