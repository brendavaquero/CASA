package org.casa.backend.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import org.casa.backend.dto.ConvocatoriaResidenciaDto;
import org.casa.backend.entity.ConvocatoriaResidencia;
import org.casa.backend.enums.EstadoActividad;
import org.casa.backend.exception.ConvocatoriaDuplicadaException;
import org.casa.backend.exception.ResourceNotFoundException;
import org.casa.backend.mapper.ConvocatoriaResidenciaMapper;
import org.casa.backend.repository.ConvocatoriaResidenciaRepository;
import org.casa.backend.service.ConvocatoriaResidenciaService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ConvocatoriaResidenciaServiceImpl implements ConvocatoriaResidenciaService {

    private ConvocatoriaResidenciaRepository convocatoriaResidenciaRepository;
    private void validarFechasConvocatoria(ConvocatoriaResidencia c) {
        LocalDate hoy = LocalDate.now();

        if (c.getFechaInicio().isBefore(hoy)) {
            throw new IllegalArgumentException(
                "La fecha de inicio no puede ser anterior a hoy"
            );
        }

        if (c.getFechaCierre().isBefore(c.getFechaInicio())) {
            throw new IllegalArgumentException(
                "La fecha de cierre no puede ser menor a la fecha de inicio"
            );
        }

        if (c.getFechaResultados().isBefore(c.getFechaCierre())) {
            throw new IllegalArgumentException(
                "La fecha de resultados no puede ser menor a la fecha de cierre"
            );
        }
    }

    private void validarFechasEvaluacion(ConvocatoriaResidencia c) {
        LocalDate hoy = LocalDate.now();

        //Fecha inicio >= hoy
        if (c.getFechaInicio().isBefore(hoy)) {
            throw new IllegalArgumentException(
                "La fecha de inicio no puede ser anterior a hoy"
            );
        }

        //Fecha cierre >= fecha inicio
        if (c.getFechaCierre().isBefore(c.getFechaInicio())) {
            throw new IllegalArgumentException(
                "La fecha de cierre no puede ser menor a la fecha de inicio"
            );
        }

        //Fecha resultados >= fecha cierre
        if (c.getFechaResultados().isBefore(c.getFechaCierre())) {
            throw new IllegalArgumentException(
                "La fecha de resultados no puede ser menor a la fecha de cierre"
            );
        }

        //INICIO EVALUACIÓN >= FECHA CIERRE
        if (c.getFechaInicioR1().isBefore(c.getFechaCierre())) {
            throw new IllegalArgumentException(
                "La fecha de inicio de evaluación debe ser mayor o igual a la fecha de cierre"
            );
        }

        //FIN EVALUACIÓN >= INICIO EVALUACIÓN
        if (c.getFechaLimiteR1().isBefore(c.getFechaInicioR1())) {
            throw new IllegalArgumentException(
                "La fecha límite de evaluación debe ser mayor o igual a la fecha de inicio"
            );
        }

        //FIN EVALUACIÓN <= FECHA RESULTADOS
        if (c.getFechaLimiteR1().isAfter(c.getFechaResultados())) {
            throw new IllegalArgumentException(
                "La fecha límite de evaluación no puede ser mayor a la fecha de resultados"
            );
        }
    }

    private void validarFechasRonda(ConvocatoriaResidencia c) {

        if (c.getFechaInicioR1().isBefore(c.getFechaCierre())) {
            throw new IllegalArgumentException(
                "La fecha de inicio de evaluación debe ser mayor o igual a la fecha de cierre"
            );
        }

        if (c.getFechaLimiteR1().isBefore(c.getFechaInicioR1())) {
            throw new IllegalArgumentException(
                "La fecha límite de evaluación debe ser mayor o igual a la fecha de inicio"
            );
        }

        if (c.getFechaLimiteR1().isAfter(c.getFechaResultados())) {
            throw new IllegalArgumentException(
                "La fecha límite de evaluación no puede ser mayor a la fecha de resultados"
            );
        }
    }

    @Override
    public ConvocatoriaResidenciaDto createConvocatoriaResi(ConvocatoriaResidenciaDto convocatoriaResiDto, MultipartFile imagen, MultipartFile bases) {
        try {
            String urlImagen = null;
            String urlBases = null;

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

            convocatoria.setEstado(EstadoActividad.AUTORIZADA);
            convocatoria.setImagen(urlImagen);
            convocatoria.setBases(urlBases);
            convocatoria.setVisible(true);
            validarConvocatoriaDuplicada(convocatoria);

            validarFechasConvocatoria(convocatoria);

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
    public ConvocatoriaResidenciaDto updateConvocatoriaResi(String convocatoriaId, ConvocatoriaResidenciaDto updatedCR) {
        ConvocatoriaResidencia convoResi = convocatoriaResidenciaRepository.findById(convocatoriaId).orElseThrow(
                () -> new ResourceNotFoundException("Convocatoria no encontrada ID: "+ convocatoriaId));
        convoResi.setBases(updatedCR.getBases());
        convoResi.setTitulo(updatedCR.getTitulo());
        convoResi.setDescripcion(updatedCR.getDescripcion());
        convoResi.setPremio(updatedCR.getPremio());
        convoResi.setConvocantes(updatedCR.getConvocantes());
        convoResi.setFechaInicioR1(updatedCR.getFechaInicioR1());
        convoResi.setFechaLimiteR1(updatedCR.getFechaLimiteR1());

        ConvocatoriaResidencia updatedConvoResiObj = convocatoriaResidenciaRepository.save(convoResi);
        return ConvocatoriaResidenciaMapper.mapToConvocatoriaResidenciaDto(updatedConvoResiObj);
    }


    @Override
    public ConvocatoriaResidenciaDto updateConvocatoriaResi(String idConvocatoria,ConvocatoriaResidenciaDto dto,MultipartFile imagen,MultipartFile bases) {

        try {

            ConvocatoriaResidencia convocatoria = convocatoriaResidenciaRepository
                    .findById(idConvocatoria)
                    .orElseThrow(() -> new RuntimeException("Convocatoria no encontrada"));

            if (imagen != null && !imagen.isEmpty()) {

                eliminarArchivoSiExiste(convocatoria.getImagen());

                String originalName = imagen.getOriginalFilename();
                String extension = originalName.substring(originalName.lastIndexOf(".") + 1).toLowerCase();

                if (!(extension.equals("jpg") || extension.equals("jpeg") || extension.equals("png"))) {
                    throw new RuntimeException("Solo se permiten imágenes JPG, JPEG o PNG");
                }

                String folder = "uploads/convocatorias/imagenes/";
                new File(folder).mkdirs();

                String fileName = System.currentTimeMillis() + "_" + originalName;
                Path path = Paths.get(folder + fileName);
                Files.copy(imagen.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

                convocatoria.setImagen("/uploads/convocatorias/imagenes/" + fileName);
            }
            if (bases != null && !bases.isEmpty()) {
                eliminarArchivoSiExiste(convocatoria.getBases());

                String originalName = bases.getOriginalFilename();
                String extension = originalName.substring(originalName.lastIndexOf(".") + 1).toLowerCase();

                if (!extension.equals("pdf")) {
                    throw new RuntimeException("El archivo de bases debe ser PDF");
                }

                String folder = "uploads/convocatorias/bases/";
                new File(folder).mkdirs();

                String fileName = System.currentTimeMillis() + "_" + originalName;
                Path path = Paths.get(folder + fileName);
                Files.copy(bases.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

                convocatoria.setBases("/uploads/convocatorias/bases/" + fileName);
            }
            convocatoria.setTitulo(dto.getTitulo());
            convocatoria.setDescripcion(dto.getDescripcion());
            convocatoria.setPremio(dto.getPremio());
            convocatoria.setConvocantes(dto.getConvocantes());
            convocatoria.setFechaInicio(dto.getFechaInicio());
            convocatoria.setFechaCierre(dto.getFechaCierre());
            convocatoria.setFechaResultados(dto.getFechaResultados());
            convocatoria.setFechaInicioR1(dto.getFechaInicioR1());
            convocatoria.setFechaLimiteR1(dto.getFechaLimiteR1());
            validarFechasConvocatoria(convocatoria);
            validarFechasEvaluacion(convocatoria);

            ConvocatoriaResidencia updated = convocatoriaResidenciaRepository.save(convocatoria);

            return ConvocatoriaResidenciaMapper.mapToConvocatoriaResidenciaDto(updated);

        } catch (IOException e) {
            throw new RuntimeException("Error al actualizar convocatoria");
        }
    }


    @Override
    public ConvocatoriaResidenciaDto updateFechaRonda(String idConvocatoria, ConvocatoriaResidenciaDto updatedCR) {
        ConvocatoriaResidencia convoResi = convocatoriaResidenciaRepository.findById(idConvocatoria).orElseThrow(
                () -> new ResourceNotFoundException("Convocatoria no encontrada ID: "+ idConvocatoria));
        convoResi.setFechaInicioR1(updatedCR.getFechaInicioR1());
        convoResi.setFechaLimiteR1(updatedCR.getFechaLimiteR1());
        validarFechasRonda(convoResi);

        ConvocatoriaResidencia updatedConvoResiObj = convocatoriaResidenciaRepository.save(convoResi);
        return ConvocatoriaResidenciaMapper.mapToConvocatoriaResidenciaDto(updatedConvoResiObj);
    }

    @Override
    @Transactional
    //2min @Scheduled(cron = "0 */2 * * * *")
    @Scheduled(cron = "0 0 0/12 * * *")
    public void actualizarEstadosConvocatorias() {
        LocalDate hoy = LocalDate.now();

        List<ConvocatoriaResidencia> convocatorias = convocatoriaResidenciaRepository.findAll();

        for (ConvocatoriaResidencia c : convocatorias) {

           if (!hoy.isBefore(c.getFechaInicio()) 
                    && hoy.isBefore(c.getFechaCierre())) {
                c.setEstado(EstadoActividad.CONVOCATORIA_ABIERTA);

            } else if (!hoy.isBefore(c.getFechaCierre()) 
                    && hoy.isBefore(c.getFechaResultados())) {
                c.setEstado(EstadoActividad.CONVOCATORIA_CERRADA);

            } else if (!hoy.isBefore(c.getFechaResultados())) {
                c.setEstado(EstadoActividad.FINALIZADA);
            }
        }
    }


    @Override
    public void validarConvocatoriaDuplicada(ConvocatoriaResidencia c) {
         boolean existe = convocatoriaResidenciaRepository
            .existsByTituloIgnoreCaseAndFechaInicioAndFechaCierreAndFechaResultados(
                c.getTitulo(),
                c.getFechaInicio(),
                c.getFechaCierre(),
                c.getFechaResultados()
            );

        if (existe) {
            throw new ConvocatoriaDuplicadaException(
                "Esta convocatoria ya existe con las mismas fechas"
            );
        }
    }

}
