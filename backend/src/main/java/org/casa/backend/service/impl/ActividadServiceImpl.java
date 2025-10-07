package org.casa.backend.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.casa.backend.dto.ActividadDto;
import org.casa.backend.mapper.ActividadMapper;
import org.casa.backend.repository.ActividadRepository;
import org.casa.backend.service.ActividadService;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ActividadServiceImpl implements ActividadService {

    private final ActividadRepository actividadRepository;

    @Override
    public List<ActividadDto> listarActividades() {
        return actividadRepository.findAll()
                .stream()
                .map(ActividadMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ActividadDto getActividadById(String idActividad) {
        return actividadRepository.findById(idActividad)
                .map(ActividadMapper::mapToDto)
                .orElse(null);
    }

    /*
    @Override
    public ActividadDto createActividad(ActividadDto actividadDto) {
        Actividad actividad = new Actividad();
        actividad.setTitulo(actividadDto.getTitulo());
        actividad.setDescripcion(actividadDto.getDescripcion());
        actividad.setFechaInicio(actividadDto.getFechaInicio());
        actividad.setFechaCierre(actividadDto.getFechaCierre());
        actividad.setFechaResultados(actividadDto.getFechaResultados());
        actividad.setFechaCreacion(actividadDto.getFechaCreacion());
        actividad.setRequisitos(actividadDto.getRequisitos());
        actividad.setEstado(actividadDto.getEstado());

        Actividad guardada = actividadRepository.save(actividad);
        return ActividadMapper.mapToDto(guardada);
    }*/

    @Override
    public void deleteActividad(String idActividad) {
        actividadRepository.deleteById(idActividad);
    }
}
