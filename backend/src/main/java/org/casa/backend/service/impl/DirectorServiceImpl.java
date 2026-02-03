package org.casa.backend.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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

    // ======================
    // CREAR DIRECTOR
    // ======================
    @Override
    public DirectorDto crear(String nombre, MultipartFile firma) {

        try {
            Director director = new Director();
            director.setNombre(nombre);

            if (firma != null && !firma.isEmpty()) {
                director.setFirma(guardarFirma(firma));
            }

            Director saved = directorRepository.save(director);
            return DirectorMapper.toDTO(saved);

        } catch (IOException e) {
            throw new RuntimeException("Error al crear director: " + e.getMessage());
        }
    }

    // ======================
    // ACTUALIZAR DIRECTOR
    // ======================
    @Override
    public DirectorDto actualizar(Long id, String nombre, MultipartFile firma) {

        try {
            Director director = directorRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Director no encontrado"));

            director.setNombre(nombre);

            if (firma != null && !firma.isEmpty()) {
                eliminarArchivoSiExiste(director.getFirma());
                director.setFirma(guardarFirma(firma));
            }

            Director updated = directorRepository.save(director);
            return DirectorMapper.toDTO(updated);

        } catch (IOException e) {
            throw new RuntimeException("Error al actualizar director: " + e.getMessage());
        }
    }

    // ======================
    // LISTAR
    // ======================
    @Override
    public List<DirectorDto> listar() {
        return directorRepository.findAll()
                .stream()
                .map(DirectorMapper::toDTO)
                .toList();
    }

    // ======================
    // ELIMINAR
    // ======================
    @Override
    public void eliminar(Long id) {
        Director director = directorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Director no encontrado"));

        eliminarArchivoSiExiste(director.getFirma());
        directorRepository.delete(director);
    }

    // ======================
    // GUARDAR FIRMA
    // ======================
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

    // ======================
    // ELIMINAR FIRMA
    // ======================
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
