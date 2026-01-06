package org.casa.backend.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Collectors;

import org.casa.backend.dto.ConvocatoriaResidenciaDto;
import org.casa.backend.entity.ConvocatoriaResidencia;
import org.casa.backend.enums.EstadoActividad;
import org.casa.backend.exception.ResourceNotFoundException;
import org.casa.backend.mapper.ConvocatoriaResidenciaMapper;
import org.casa.backend.repository.ConvocatoriaResidenciaRepository;
import org.casa.backend.service.ConvocatoriaResidenciaService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ConvocatoriaResidenciaServiceImpl implements ConvocatoriaResidenciaService {

    private ConvocatoriaResidenciaRepository convocatoriaResidenciaRepository;

    @Override
    public ConvocatoriaResidenciaDto createConvocatoriaResi(ConvocatoriaResidenciaDto convocatoriaResiDto, MultipartFile imagen, MultipartFile bases) {
        /*ConvocatoriaResidencia convocatoriaResidencia = ConvocatoriaResidenciaMapper.mapConvocatoriaResidencia(convocatoriaResiDto);
        ConvocatoriaResidencia savedConvocatoriaResi = convocatoriaResidenciaRepository.save(convocatoriaResidencia);
        return ConvocatoriaResidenciaMapper.mapToConvocatoriaResidenciaDto(savedConvocatoriaResi);*/
        try {
            String urlImagen = null;
            String urlBases = null;

            //para la imagen
            if (imagen != null && !imagen.isEmpty()) {

                String originalName = imagen.getOriginalFilename();
                String extension = originalName.substring(originalName.lastIndexOf(".") + 1).toLowerCase();

                if (!(extension.equals("jpg") || extension.equals("jpeg") || extension.equals("png"))) {
                    throw new RuntimeException("Solo se permiten imágenes JPG, JPEG o PNG");
                }

                String folder = "uploads/convocatorias/imagenes/";
                File directory = new File(folder);
                if (!directory.exists()) {
                    directory.mkdirs();
                }

                String fileName = System.currentTimeMillis() + "_" + originalName;
                Path path = Paths.get(folder + fileName);
                Files.copy(imagen.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

                urlImagen = "/uploads/convocatorias/imagenes/" + fileName;
            }

            //para el pdf
            if (bases != null && !bases.isEmpty()) {

                String originalName = bases.getOriginalFilename();
                String extension = originalName.substring(originalName.lastIndexOf(".") + 1).toLowerCase();

                if (!extension.equals("pdf")) {
                    throw new RuntimeException("El archivo de bases debe ser PDF");
                }

                String folder = "uploads/convocatorias/bases/";
                File directory = new File(folder);
                if (!directory.exists()) {
                    directory.mkdirs();
                }

                String fileName = System.currentTimeMillis() + "_" + originalName;
                Path path = Paths.get(folder + fileName);
                Files.copy(bases.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

                urlBases = "/uploads/convocatorias/bases/" + fileName;
            }

            //Guardar la convocatoria
            ConvocatoriaResidencia convocatoria =
                    ConvocatoriaResidenciaMapper.mapConvocatoriaResidencia(convocatoriaResiDto);

            convocatoria.setEstado(EstadoActividad.PENDIENTE);
            convocatoria.setImagen(urlImagen);
            convocatoria.setBases(urlBases);

            ConvocatoriaResidencia saved =
                    convocatoriaResidenciaRepository.save(convocatoria);

            return ConvocatoriaResidenciaMapper.mapToConvocatoriaResidenciaDto(saved);

        } catch (IOException e) {
            throw new RuntimeException("Error al crear convocatoria: " + e.getMessage());
        }
    }

    @Override
    public ConvocatoriaResidenciaDto getConvocatoriaResiById(String idActividad) {
        ConvocatoriaResidencia convocatoriaResi = convocatoriaResidenciaRepository.findById(idActividad)
                .orElseThrow(() -> new ResourceNotFoundException("Convocatoria/residencia no encontrada con ID: "+idActividad));
        return ConvocatoriaResidenciaMapper.mapToConvocatoriaResidenciaDto(convocatoriaResi);
    }

    @Override
    public List<ConvocatoriaResidenciaDto> getAllConvocatoriaResi() {
        List<ConvocatoriaResidencia> convocatoriasResis = convocatoriaResidenciaRepository.findAll();
        return convocatoriasResis.stream().map((convocatoriaResi) -> ConvocatoriaResidenciaMapper.mapToConvocatoriaResidenciaDto(convocatoriaResi))
                .collect(Collectors.toList());
    }

    @Override
    public ConvocatoriaResidenciaDto updateConvocatoriaResi(String convocatoriaId, ConvocatoriaResidenciaDto updatedCR) {
        ConvocatoriaResidencia convoResi = convocatoriaResidenciaRepository.findById(convocatoriaId).orElseThrow(
                () -> new ResourceNotFoundException("Convocatoria no encontrada ID: "+ convocatoriaId));
        convoResi.setBases(updatedCR.getBases());
        convoResi.setPremio(updatedCR.getPremio());
        convoResi.setConvocantes(updatedCR.getConvocantes());

        ConvocatoriaResidencia updatedConvoResiObj = convocatoriaResidenciaRepository.save(convoResi);
        return ConvocatoriaResidenciaMapper.mapToConvocatoriaResidenciaDto(updatedConvoResiObj);
    }

}
