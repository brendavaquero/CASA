package org.casa.backend.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Collectors;

import org.casa.backend.dto.InstitucionDTO;
import org.casa.backend.entity.Institucion;
import org.casa.backend.exception.ResourceNotFoundException;
import org.casa.backend.mapper.InstitucionMapper;
import org.casa.backend.repository.InstitucionRepository;
import org.casa.backend.service.InstitucionService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class InstitucionServiceImpl implements InstitucionService {

    private InstitucionRepository institucionRepository;

    private static final String LOGOS_FOLDER = "uploads/instituciones/logos/";

    @Override
    public InstitucionDTO createInstitucion(InstitucionDTO dto, MultipartFile logo) {
        try {
            String logoUrl = null;

            if (logo != null && !logo.isEmpty()) {

                String originalName = logo.getOriginalFilename();
                String extension = originalName
                        .substring(originalName.lastIndexOf(".") + 1)
                        .toLowerCase();

                if (!(extension.equals("jpg") || extension.equals("jpeg") || extension.equals("png"))) {
                    throw new RuntimeException("Solo se permiten imágenes JPG, JPEG o PNG");
                }

                File directory = new File(LOGOS_FOLDER);
                if (!directory.exists()) {
                    directory.mkdirs();
                }

                String fileName = System.currentTimeMillis() + "_" + originalName;
                Path path = Paths.get(LOGOS_FOLDER + fileName);
                Files.copy(logo.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

                logoUrl = "/uploads/instituciones/logos/" + fileName;
            }

            Institucion institucion = InstitucionMapper.mapToInstitucion(dto);
            institucion.setLogoUrl(logoUrl);
            institucion.setActivo(true);

            Institucion saved = institucionRepository.save(institucion);
            return InstitucionMapper.mapToDto(saved);

        } catch (IOException e) {
            throw new RuntimeException("Error al crear institución: " + e.getMessage());
        }
    }

    @Override
    public InstitucionDTO getInstitucionById(Long id) {
        Institucion institucion = institucionRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Institución no encontrada con ID: " + id)
                );

        return InstitucionMapper.mapToDto(institucion);
    }

    @Override
    public List<InstitucionDTO> getAllInstituciones() {
        return institucionRepository.findAll()
                .stream()
                .map(InstitucionMapper::mapToDto)
                .collect(Collectors.toList());
    }

    private void eliminarArchivoSiExiste(String ruta) {
        try {
            if (ruta != null && !ruta.isBlank()) {
                Path path = Paths.get(ruta.substring(1)).normalize();
                Files.deleteIfExists(path);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al eliminar archivo: " + ruta);
        }
    }

    @Override
    @Transactional
    public InstitucionDTO updateInstitucion(Long id, InstitucionDTO dto, MultipartFile logo) {
        try {
            Institucion institucion = institucionRepository.findById(id)
                    .orElseThrow(() ->
                            new ResourceNotFoundException("Institución no encontrada con ID: " + id)
                    );

            if (logo != null && !logo.isEmpty()) {

                eliminarArchivoSiExiste(institucion.getLogoUrl());

                String originalName = logo.getOriginalFilename();
                String extension = originalName
                        .substring(originalName.lastIndexOf(".") + 1)
                        .toLowerCase();

                if (!(extension.equals("jpg") || extension.equals("jpeg") || extension.equals("png"))) {
                    throw new RuntimeException("Solo se permiten imágenes JPG, JPEG o PNG");
                }

                new File(LOGOS_FOLDER).mkdirs();

                String fileName = System.currentTimeMillis() + "_" + originalName;
                Path path = Paths.get(LOGOS_FOLDER + fileName);
                Files.copy(logo.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

                institucion.setLogoUrl("/uploads/instituciones/logos/" + fileName);
            }

            institucion.setNombre(dto.getNombre());
            institucion.setActivo(dto.isActivo());

            Institucion updated = institucionRepository.save(institucion);
            return InstitucionMapper.mapToDto(updated);

        } catch (IOException e) {
            throw new RuntimeException("Error al actualizar institución");
        }
    }

    @Override
    public void deleteInstitucion(Long id) {
        Institucion institucion = institucionRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Institución no encontrada con ID: " + id)
                );

        eliminarArchivoSiExiste(institucion.getLogoUrl());
        institucionRepository.delete(institucion);
    }
}
