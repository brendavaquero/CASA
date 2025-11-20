package org.casa.backend.service.impl;

import lombok.AllArgsConstructor;
import org.casa.backend.dto.ArchivoDto;
import org.casa.backend.entity.Actividad;
import org.casa.backend.entity.Archivo;
import org.casa.backend.entity.Postulacion;
import org.casa.backend.enums.TipoArchivo;
import org.casa.backend.exception.ResourceNotFoundException;
import org.casa.backend.mapper.ArchivoMapper;
import org.casa.backend.repository.ActividadRepository;
import org.casa.backend.repository.ArchivoRepository;
import org.casa.backend.repository.PostulacionRepository;
import org.casa.backend.service.ArchivoService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.io.File;
import java.io.IOException;
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
    public void deleteArchivo(String idArchivo) {
        Archivo archivo = archivoRepository.findById(idArchivo)
                .orElseThrow(() -> new ResourceNotFoundException("Archivo no encontrado. ID: " + idArchivo));
        archivoRepository.delete(archivo);
    }

    @Override
    public List<ArchivoDto> getAllArchivos() {
        List<Archivo> archivos =  archivoRepository.findAll();
        return archivos.stream().map((archivo) -> ArchivoMapper.mapToArchivoDto(archivo))
                .collect(Collectors.toList());
    }

    @Override
    public ArchivoDto uploadArchivo(MultipartFile file, String idActividad, String idPostulacion) {
        try {
            // 1. Validar archivo
            if (file.isEmpty()) {
                throw new RuntimeException("El archivo está vacío");
            }

            // 2. Crear carpeta /uploads si no existe
            String uploadDir = "uploads/";
            File directory = new File(uploadDir);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // 3. Crear nombre único
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            String filePath = uploadDir + fileName;

            // 4. Guardar archivo en el servidor
            Path path = Paths.get(filePath);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

            // 5. Convertir ruta en URL accesible
            String url = "/uploads/" + fileName;

            // 6. Crear entidad Archivo
            Archivo archivo = new Archivo();
            archivo.setNombre(file.getOriginalFilename());
            archivo.setRuta(url);
            archivo.setTipo(TipoArchivo.IMAGEN); // O imagen, depende de tu enum

            // Asociar actividad
            if (idActividad != null) {
                Actividad actividad = actividadRepository.findById(idActividad)
                        .orElseThrow(() -> new RuntimeException("Actividad no encontrada"));
                archivo.setActividad(actividad);
            }

            // Asociar postulación
            if (idPostulacion != null) {
                Postulacion postulacion = postulacionRepository.findById(idPostulacion)
                        .orElseThrow(() -> new RuntimeException("Postulación no encontrada"));
                archivo.setPostulacion(postulacion);
            }

            // 7. Guardar en BD
            Archivo saved = archivoRepository.save(archivo);

            // 8. Devolver DTO
            return ArchivoMapper.mapToArchivoDto(saved);

        } catch (IOException e) {
            throw new RuntimeException("Error al subir archivo: " + e.getMessage());
        }
    }
    @Override
    public List<ArchivoDto> getArchivosByActividad(String idActividad) {
        return archivoRepository.findByActividad_IdActividad(idActividad)
                .stream()
                .map(ArchivoMapper::mapToArchivoDto)
                .toList();
    }
}
