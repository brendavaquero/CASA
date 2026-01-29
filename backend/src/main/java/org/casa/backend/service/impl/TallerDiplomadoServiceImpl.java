package org.casa.backend.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.casa.backend.dto.ActividadDto;
import org.casa.backend.dto.TallerDiplomadoDto;
import org.casa.backend.entity.Actividad;
import org.casa.backend.entity.Docente;
import org.casa.backend.entity.Programa;
import org.casa.backend.entity.Sesion;
import org.casa.backend.entity.TallerDiplomado;
import org.casa.backend.enums.EstadoActividad;
import org.casa.backend.exception.ResourceNotFoundException;
import org.casa.backend.mapper.ActividadMapper;
import org.casa.backend.mapper.TallerDiplomadoMapper;
import org.casa.backend.repository.ActividadRepository;
import org.casa.backend.repository.DocenteRepository;
import org.casa.backend.repository.ProgramaRepository;
import org.casa.backend.repository.SesionRepository;
import org.casa.backend.repository.TallerDiplomadoRepository;
import org.casa.backend.service.TallerDiplomadoService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.transaction.Transactional;

import java.nio.file.Path;
import java.nio.file.Paths;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class TallerDiplomadoServiceImpl implements TallerDiplomadoService {

    private TallerDiplomadoRepository tallerDiplomadoRepository;
    private DocenteRepository docenteRepository;
    private ProgramaRepository programaRepository;
    private ActividadRepository actividadRepository;
    private SesionRepository sesionRepository;

    private void validarFechas(TallerDiplomado t) {
        LocalDate hoy = LocalDate.now();

        // Fecha inicio >= hoy
        if (t.getFechaInicio().isBefore(hoy)) {
            throw new IllegalArgumentException(
                "La fecha de inicio no puede ser anterior a hoy"
            );
        }

        // Fecha cierre >= fecha inicio
        if (t.getFechaCierre().isBefore(t.getFechaInicio())) {
            throw new IllegalArgumentException(
                "La fecha de cierre no puede ser menor a la fecha de inicio"
            );
        }

        // Fecha resultados >= fecha cierre
        if (t.getFechaResultados().isBefore(t.getFechaCierre())) {
            throw new IllegalArgumentException(
                "La fecha de resultados no puede ser menor a la fecha de cierre"
            );
        }
    }

    @Override
    public TallerDiplomadoDto createTallerDiplomado(TallerDiplomadoDto tallerDiplomadoDto) {
        Docente docente = docenteRepository.findById(tallerDiplomadoDto.getIdDocente())
            .orElseThrow(() -> new ResourceNotFoundException("Docente no encontrado"));

        Programa programa = null;

        if (tallerDiplomadoDto.getIdPrograma() != null) {
            programa = programaRepository.findById(tallerDiplomadoDto.getIdPrograma())
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "Programa no encontrado con id: " + tallerDiplomadoDto.getIdPrograma()
                    )
                );
        }

        TallerDiplomado tallerDiplomado = TallerDiplomadoMapper.mapToTallerDiplomado(tallerDiplomadoDto,programa,docente);
        TallerDiplomado savedTallerDiplomado = tallerDiplomadoRepository.save(tallerDiplomado);
        return TallerDiplomadoMapper.mapToTallerDiplomadoDto(savedTallerDiplomado);

    }

    @Override
    public TallerDiplomadoDto getTallerDiplomadoById(String idActividad) {
        TallerDiplomado tallerDiplomado = tallerDiplomadoRepository.findById(idActividad)
                .orElseThrow(() -> new ResourceNotFoundException("Taller/diplomado no encontrado. ID: " + idActividad));
        return TallerDiplomadoMapper.mapToTallerDiplomadoDto(tallerDiplomado);
    }

    @Override
    public List<TallerDiplomadoDto> getAllTalleresDiplomados() {
        List<TallerDiplomado> talleresDiplomados =  tallerDiplomadoRepository.findAll();
        return talleresDiplomados.stream().map((tallerDiplomado) -> TallerDiplomadoMapper.mapToTallerDiplomadoDto(tallerDiplomado))
                .collect(Collectors.toList());
    }

    @Override
    public TallerDiplomadoDto updateActividad(String actividadId, TallerDiplomadoDto updatedActividad) {
        TallerDiplomado actividad = tallerDiplomadoRepository.findById(actividadId).orElseThrow(
            () -> new ResourceNotFoundException("Actividad no encontrada con id: "+actividadId)
        );
        actividad.setFechaInicio(updatedActividad.getFechaInicio());
        actividad.setFechaCierre(updatedActividad.getFechaCierre());
        actividad.setFechaResultados(updatedActividad.getFechaResultados());
        actividad.setNumSesiones(updatedActividad.getNumSesiones());

        if (updatedActividad.getIdPrograma() != null) {
        Programa programa = programaRepository.findById(updatedActividad.getIdPrograma())
            .orElseThrow(() ->
                new ResourceNotFoundException(
                    "Programa no encontrado con id: " + updatedActividad.getIdPrograma()
                )
            );
        actividad.setPrograma(programa);
        actividad.setVisible(true);
    }
        validarFechas(actividad);
        TallerDiplomado updatedActividadObj = tallerDiplomadoRepository.save(actividad);

        return TallerDiplomadoMapper.mapToTallerDiplomadoDto(updatedActividadObj);
    }

    @Override
    public TallerDiplomadoDto updateTallerDiplo(String tallerId, TallerDiplomadoDto updatedTD) {
        TallerDiplomado tallerDiplo = tallerDiplomadoRepository.findById(tallerId).orElseThrow(
            () -> new ResourceNotFoundException("Taller/Diplomado no encontrado con id: "+tallerId)
        );
        tallerDiplo.setTitulo(updatedTD.getTitulo());
        tallerDiplo.setDescripcion(updatedTD.getDescripcion());
        tallerDiplo.setCupo(updatedTD.getCupo());
        tallerDiplo.setObjetivoGeneral(updatedTD.getObjetivoGeneral());
        tallerDiplo.setObjetivosEspecificos(updatedTD.getObjetivosEspecificos());
        tallerDiplo.setTemas(updatedTD.getTemas());
        tallerDiplo.setMaterialSol(updatedTD.getMaterialSol());
        tallerDiplo.setCriteriosSeleccion(updatedTD.getCriteriosSeleccion());
        tallerDiplo.setNotas(updatedTD.getNotas());
        TallerDiplomado updatedTallerDiploObj = tallerDiplomadoRepository.save(tallerDiplo);

        return TallerDiplomadoMapper.mapToTallerDiplomadoDto(updatedTallerDiploObj);
    }

    @Override
    public List<TallerDiplomadoDto> getTalleresByDocente(String idUsuario) {
        return tallerDiplomadoRepository.findByDocenteIdUsuario(idUsuario)
            .stream()
            .map(TallerDiplomadoMapper::mapToTallerDiplomadoDto)
            .collect(Collectors.toList());
    }

    @Override
    public ActividadDto updateEstadoAct(String idActividad, EstadoActividad estado) {
        Actividad actividad = actividadRepository.findById(idActividad)
                .orElseThrow(() -> new ResourceNotFoundException("Actividad no encontrada con id " + idActividad));

        actividad.setEstado(estado);
        Actividad updated = actividadRepository.save(actividad);

        return ActividadMapper.mapToActividadDto(updated);
    }

    //Subir imagen a actividad

    @Override
    public String uploadImagenActividad(MultipartFile file, String idActividad) {

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
            String url = "/uploads/actividades/" + fileName;

            // Actualizar entidad Actividad
            Actividad actividad = actividadRepository.findById(idActividad)
                    .orElseThrow(() -> new RuntimeException("Actividad no encontrada"));

            actividad.setImagen(url); // <==== Guarda la imagen aquí
            actividadRepository.save(actividad);

            return url;

        } catch (IOException e) {
            throw new RuntimeException("Error al subir imagen: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    @Scheduled(cron = "0 */2 * * * *")
    //@Scheduled(cron = "0 0 0/12 * * *")
    public void actualizarEstadosTalleres() {

        LocalDate hoy = LocalDate.now();
        List<TallerDiplomado> talleres = tallerDiplomadoRepository.findAll();

        for (TallerDiplomado taller : talleres) {

            EstadoActividad estadoActual = taller.getEstado();
            EstadoActividad nuevoEstado = null;

            List<Sesion> sesiones =
                    sesionRepository.findByTallerDiplomadoOrderByFechaInicioAsc(taller);

            if (!sesiones.isEmpty()) {

                LocalDate fechaInicioReal = sesiones.get(0).getFechaInicio();

                LocalDate fechaFinReal = sesiones.stream()
                        .map(s -> s.getFechaFin() != null
                                ? s.getFechaFin()
                                : s.getFechaInicio())
                        .max(LocalDate::compareTo)
                        .orElse(fechaInicioReal);

                if (!hoy.isBefore(fechaInicioReal) && !hoy.isAfter(fechaFinReal)) {
                    nuevoEstado = EstadoActividad.EN_CURSO;

                } else if (hoy.isAfter(fechaFinReal)) {
                    nuevoEstado = EstadoActividad.FINALIZADA;
                }
            }

            if (nuevoEstado == null) {

                if (taller.getFechaInicio() != null
                        && hoy.isEqual(taller.getFechaInicio())) {

                    nuevoEstado = EstadoActividad.CONVOCATORIA_ABIERTA;

                } else if (taller.getFechaCierre() != null
                        && !hoy.isBefore(taller.getFechaCierre())) {

                    nuevoEstado = EstadoActividad.CONVOCATORIA_CERRADA;
                }
            }

            if (nuevoEstado != null && nuevoEstado != estadoActual) {
                taller.setEstado(nuevoEstado);
            }
        }
    }

}
