package org.casa.backend.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Collectors;

import org.casa.backend.dto.FinalistaDto;
import org.casa.backend.dto.GanadorConvocatoriaDto;
import org.casa.backend.dto.GanadorDto;
import org.casa.backend.entity.*;
import org.casa.backend.exception.ResourceNotFoundException;
import org.casa.backend.mapper.GanadorMapper;
import org.casa.backend.repository.*;
import org.casa.backend.service.GanadorService;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@AllArgsConstructor
public class GanadorServiceImpl implements GanadorService {

    private GanadorRepository ganadorRepository;
    private EvaluacionRepository evaluacionRepository;
    private ArchivoRepository archivoRepository;
    private ResultadoRondaUnoRepository resultadoRepository;
    private ActividadRepository actividadRepository;

    @Override
    public List<GanadorDto> getAllGanadores() {
        return ganadorRepository.findAll()
                .stream()
                .map(GanadorMapper::mapToGanadorDto)
                .collect(Collectors.toList());
    }

    @Override
    public GanadorDto createGanador(GanadorDto ganadorDto) {
        ResultadoRondaUno resultado = resultadoRepository.findById(ganadorDto.getIdResultado())
                .orElseThrow(() -> new ResourceNotFoundException("evaluacion no encontrada"));

        Archivo archivo = archivoRepository.findById(ganadorDto.getIdArchivo())
                .orElseThrow(() -> new ResourceNotFoundException("archivo no encontrado"));

        Ganador ganador = new Ganador();
        ganador.setResultado(resultado);
        ganador.setSemblanza(ganadorDto.getSemblanza());
        ganador.setFoto(ganadorDto.getFoto());
        ganador.setArchivo(archivo);

        Ganador saved = ganadorRepository.save(ganador);
        return GanadorMapper.mapToGanadorDto(saved);
    }

    @Override
    public GanadorDto getGanadorById(String idGanador) {
        Ganador ganador = ganadorRepository.findById(idGanador)
                .orElseThrow(() -> new ResourceNotFoundException("No existe un ganador con id: " + idGanador));
        return GanadorMapper.mapToGanadorDto(ganador);
    }

    @Override
    public GanadorDto updateGanador(String idGanador, GanadorDto ganadorDto) {
        Ganador ganador = ganadorRepository.findById(idGanador)
                .orElseThrow(() -> new ResourceNotFoundException("No existe un ganador con id: " + idGanador));

        ganador.setSemblanza(ganadorDto.getSemblanza());
        ganador.setFoto(ganadorDto.getFoto());

        Ganador updated = ganadorRepository.save(ganador);

        return GanadorMapper.mapToGanadorDto(updated);
    }

    @Override
    public void seleccionarGanador(String idResultado) {
        ResultadoRondaUno resultado = resultadoRepository.findById(idResultado)
                .orElseThrow(() -> new RuntimeException("Resultado no encontrado"));
        Archivo archivo = archivoRepository
                .findByPostulacion_IdPostulacion(resultado.getIdPostulacion())
                .orElseThrow(() -> new RuntimeException("Archivo no encontrado"));

        Ganador ganador = new Ganador();
        ganador.setResultado(resultado);
        ganador.setArchivo(archivo);
        ganador.setSemblanza(null);
        ganador.setFoto(null);
        ganadorRepository.save(ganador);
    }

    @Override
    public String uploadImagen(MultipartFile file, String idGanador) {

        try {
            if (file.isEmpty()) {
                throw new RuntimeException("La imagen está vacía");
            }

            // Validar extensión
            String originalName = file.getOriginalFilename();
            String extension = originalName.substring(originalName.lastIndexOf(".") + 1).toLowerCase();

            if (!(extension.equals("jpg") || extension.equals("jpeg") || extension.equals("png"))) {
                throw new RuntimeException("Solo se permiten imágenes JPG, JPEG o PNG");
            }

            // Carpeta donde guardar imágenes
            String folder = "uploads/actividades/";

            File directory = new File(folder);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Crear nombre único
            String fileName = System.currentTimeMillis() + "_" + originalName;
            String filePath = folder + fileName;

            // Guardar
            Path path = Paths.get(filePath);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

            // URL accesible desde el front
            String url = "/uploads/ganadores/" + fileName;

            // Actualizar entidad Actividad
            Ganador ganador = ganadorRepository.findById(idGanador)
                    .orElseThrow(() -> new RuntimeException("Ganador no encontrado"));

            ganador.setFoto(url);
            ganadorRepository.save(ganador);

            return url;

        } catch (IOException e) {
            throw new RuntimeException("Error al subir imagen: " + e.getMessage());
        }
    }

    @Override
    public List<GanadorConvocatoriaDto> obtenerGanadoresPorConvocatoria(String idConvocatoria) {
        return ganadorRepository
                .findByResultado_IdConvocatoria(idConvocatoria)
                .stream()
                .map(GanadorConvocatoriaDto::new)
                .toList();
    }
}
