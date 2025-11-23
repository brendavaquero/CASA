package org.casa.backend.service.impl;

import lombok.AllArgsConstructor;
import org.casa.backend.dto.PostulacionDto;
import org.casa.backend.dto.TallerDiplomadoDto;
import org.casa.backend.entity.Docente;
import org.casa.backend.entity.Postulacion;
import org.casa.backend.entity.Programa;
import org.casa.backend.entity.TallerDiplomado;
import org.casa.backend.enums.EstadoPost;
import org.casa.backend.exception.ResourceNotFoundException;
import org.casa.backend.mapper.PostulacionMapper;
import org.casa.backend.mapper.TallerDiplomadoMapper;
import org.casa.backend.repository.DocenteRepository;
import org.casa.backend.repository.PostulacionRepository;
import org.casa.backend.repository.ProgramaRepository;
import org.casa.backend.repository.TallerDiplomadoRepository;
import org.casa.backend.service.TallerDiplomadoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TallerDiplomadoServiceImpl implements TallerDiplomadoService {

    private TallerDiplomadoRepository tallerDiplomadoRepository;
    private DocenteRepository docenteRepository;
    private ProgramaRepository programaRepository;
    private PostulacionRepository postulacionRepository;

    @Override
    public TallerDiplomadoDto createTallerDiplomado(TallerDiplomadoDto tallerDiplomadoDto) {
        Docente docente = docenteRepository.findById(tallerDiplomadoDto.getIdUsuario())
            .orElseThrow(() -> new ResourceNotFoundException("Docente no encontrado"));

        Programa programa = programaRepository.findById(tallerDiplomadoDto.getIdPrograma())
            .orElseThrow(() -> new ResourceNotFoundException("Programa no encontrado"));


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
        actividad.setTitulo(updatedActividad.getTitulo());
        actividad.setDescripcion(updatedActividad.getDescripcion());
        actividad.setFechaInicio(updatedActividad.getFechaInicio());
        actividad.setFechaCierre(updatedActividad.getFechaCierre());
        actividad.setFechaResultados(updatedActividad.getFechaResultados());
        actividad.setRequisitos(updatedActividad.getRequisitos());
        actividad.setEstado(updatedActividad.getEstado());
        TallerDiplomado updatedActividadObj = tallerDiplomadoRepository.save(actividad);

        return TallerDiplomadoMapper.mapToTallerDiplomadoDto(updatedActividadObj);
    }

    @Override
    public TallerDiplomadoDto updateTallerDiplo(String tallerId, TallerDiplomadoDto updatedTD) {
        TallerDiplomado tallerDiplo = tallerDiplomadoRepository.findById(tallerId).orElseThrow(
            () -> new ResourceNotFoundException("Taller/Diplomado no encontrado con id: "+tallerId)
        );
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
    public List<PostulacionDto> obtenerPostulacionesPendientes(String idActividad) {

        // 1. Validar que la actividad exista
        TallerDiplomado actividad = tallerDiplomadoRepository.findById(idActividad)
                .orElseThrow(() -> new ResourceNotFoundException("Actividad no encontrada"));

        // 2. Obtener postulaciones pendientes asociadas a esta actividad
        List<Postulacion> postulaciones = postulacionRepository
                .findByActividad_IdActividadAndEstadoPos(idActividad, EstadoPost.PENDIENTE);

        // 3. Convertir a DTOs
        return postulaciones.stream()
                .map(PostulacionMapper::mapToPostulacionDto)
                .toList();
    }


}
