package org.casa.backend.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.casa.backend.dto.GanadorDto;
import org.casa.backend.entity.Evaluacion;
import org.casa.backend.entity.Ganador;
import org.casa.backend.exception.ResourceNotFoundException;
import org.casa.backend.mapper.GanadorMapper;
import org.casa.backend.repository.EvaluacionRepository;
import org.casa.backend.repository.GanadorRepository;
import org.casa.backend.service.GanadorService;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GanadorServiceImpl implements GanadorService {

    private GanadorRepository ganadorRepository;
    private EvaluacionRepository evaluacionRepository;

    @Override
    public List<GanadorDto> getAllGanadores() {
        return ganadorRepository.findAll()
            .stream()
            .map(GanadorMapper::mapToGanadorDto)
            .collect(Collectors.toList());
    }

    @Override
    public GanadorDto createGanador(GanadorDto ganadorDto) {
        Evaluacion evaluacion = evaluacionRepository.findById(ganadorDto.getIdEvaluacion())
            .orElseThrow(() -> new ResourceNotFoundException("evaluacion no encontrada"));

        Ganador ganador = new Ganador();
        ganador.setEvaluacion(evaluacion);
        ganador.setSemblanza(ganadorDto.getSemblanza());
        ganador.setFoto(ganadorDto.getFoto());

        Ganador saved = ganadorRepository.save(ganador);
        return GanadorMapper.mapToGanadorDto(saved);
    }

    @Override
    public GanadorDto getGanadorById(String idGanador) {
        Ganador ganador = ganadorRepository.findById(idGanador)
            .orElseThrow(() -> new ResourceNotFoundException("No existe un ganador con id: "+idGanador));
        return GanadorMapper.mapToGanadorDto(ganador);
    }

    @Override
    public GanadorDto updateGanador(String idGanador, GanadorDto ganadorDto) {
        Ganador ganador = ganadorRepository.findById(idGanador)
            .orElseThrow(() -> new ResourceNotFoundException("No existe un ganador con id: "+idGanador));

        ganador.setSemblanza(ganadorDto.getSemblanza());
        ganador.setFoto(ganadorDto.getFoto());

        Ganador updated = ganadorRepository.save(ganador);

        return GanadorMapper.mapToGanadorDto(updated);
    }
    
}
