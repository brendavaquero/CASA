package org.casa.backend.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.casa.backend.dto.DirectorDto;
import org.casa.backend.entity.Director;
import org.casa.backend.mapper.DirectorMapper;
import org.casa.backend.repository.DirectorRepository;
import org.casa.backend.service.DirectorService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DirectorServiceImpl implements DirectorService {

    private DirectorRepository directorRepository;

    private static final String FOLDER_FIRMAS = "uploads/firmas/";

    @Override
    public DirectorDto crear(
            String nombre,
            MultipartFile firma,
            Boolean activo,
            LocalDate fechaInicio,
            LocalDate fechaFin
    ) {

        try {
            validarDirector(activo, fechaInicio, fechaFin);

            if (Boolean.TRUE.equals(activo)) {
                desactivarDirectorActivo();
            }

            Director director = new Director();
            director.setNombre(nombre);
            director.setActivo(activo != null ? activo : false);
            director.setFechaInicio(fechaInicio);
            director.setFechaFin(fechaFin);

            if (firma != null && !firma.isEmpty()) {
                director.setFirma(guardarFirma(firma));
            }

            return DirectorMapper.toDTO(directorRepository.save(director));

        } catch (IOException e) {
            throw new RuntimeException("Error al crear director: " + e.getMessage());
        }
    }

    @Override
    public DirectorDto actualizar(
            Long id,
            String nombre,
            MultipartFile firma,
            Boolean activo,
            LocalDate fechaInicio,
            LocalDate fechaFin
    ) {

        try {
            validarDirector(activo, fechaInicio, fechaFin);

            Director director = directorRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Director no encontrado"));

            if (Boolean.TRUE.equals(activo) && !director.getActivo()) {
                desactivarDirectorActivo();
            }

            director.setNombre(nombre);
            director.setActivo(activo);
            director.setFechaInicio(fechaInicio);
            director.setFechaFin(fechaFin);

            if (firma != null && !firma.isEmpty()) {
                eliminarArchivoSiExiste(director.getFirma());
                director.setFirma(guardarFirma(firma));
            }

            return DirectorMapper.toDTO(directorRepository.save(director));

        } catch (IOException e) {
            throw new RuntimeException("Error al actualizar director: " + e.getMessage());
        }
    }


    @Override
    public List<DirectorDto> listar() {
        return directorRepository.findAll()
                .stream()
                .map(DirectorMapper::toDTO)
                .toList();
    }

    @Override
    public DirectorDto obtenerActivo() {
        Director director = directorRepository.findByActivoTrue();
        return DirectorMapper.toDTO(director);
    }


    @Override
    public void eliminar(Long id) {
        Director director = directorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Director no encontrado"));

        if (Boolean.TRUE.equals(director.getActivo())) {
            throw new RuntimeException("No se puede eliminar el director activo");
        }

        eliminarArchivoSiExiste(director.getFirma());
        directorRepository.delete(director);
    }


    private void validarDirector(Boolean activo, LocalDate fechaInicio, LocalDate fechaFin) {

        if (fechaInicio != null && fechaFin != null && fechaFin.isBefore(fechaInicio)) {
            throw new RuntimeException("La fecha fin no puede ser menor a la fecha inicio");
        }

        if (Boolean.TRUE.equals(activo) && fechaFin != null) {
            throw new RuntimeException("Un director activo no puede tener fecha fin");
        }

        if (Boolean.FALSE.equals(activo) && fechaFin == null && fechaInicio != null) {
            throw new RuntimeException("Un director inactivo debe tener fecha fin");
        }
    }


    private void desactivarDirectorActivo() {
        Director activo = directorRepository.findByActivoTrue();
        if (activo != null) {
            activo.setActivo(false);
            activo.setFechaFin(LocalDate.now());
            directorRepository.save(activo);
        }
    }


    private String guardarFirma(MultipartFile archivo) throws IOException {

        String originalName = archivo.getOriginalFilename();
        String extension = originalName
                .substring(originalName.lastIndexOf(".") + 1)
                .toLowerCase();

        if (!(extension.equals("jpg") || extension.equals("jpeg") || extension.equals("png"))) {
            throw new RuntimeException("La firma debe ser una imagen JPG, JPEG o PNG");
        }

        File directory = new File(FOLDER_FIRMAS);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        String fileName = UUID.randomUUID() + "_" + originalName.replaceAll("\\s+", "_");
        Path path = Paths.get(FOLDER_FIRMAS + fileName);

        Files.copy(
                archivo.getInputStream(),
                path,
                StandardCopyOption.REPLACE_EXISTING
        );

        return "/uploads/firmas/" + fileName;
    }


    private void eliminarArchivoSiExiste(String ruta) {
        try {
            if (ruta != null && !ruta.isBlank()) {
                Path path = Paths.get(ruta.substring(1)).normalize();
                Files.deleteIfExists(path);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al eliminar firma: " + ruta);
        }
    }
}
