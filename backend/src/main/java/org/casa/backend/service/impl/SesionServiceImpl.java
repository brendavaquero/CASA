package org.casa.backend.service.impl;

import lombok.AllArgsConstructor;
import org.casa.backend.dto.SesionDto;
import org.casa.backend.entity.Sesion;
import org.casa.backend.entity.TallerDiplomado;
import org.casa.backend.exception.ResourceNotFoundException;
import org.casa.backend.mapper.SesionMapper;
import org.casa.backend.repository.SesionRepository;
import org.casa.backend.repository.TallerDiplomadoRepository;
import org.casa.backend.service.SesionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor

public class SesionServiceImpl implements SesionService {

    private SesionRepository sesionRepository;
    private TallerDiplomadoRepository tallerDiplomadoRepository;

    @Override
    public SesionDto createSesion(SesionDto sesionDto) {
        TallerDiplomado taller = tallerDiplomadoRepository.findById(sesionDto.getIdTallerDiplomado())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Taller/Diplomado no encontrado. ID: " + sesionDto.getIdTallerDiplomado()));
        Sesion sesion = SesionMapper.mapToSesion(sesionDto, taller);
        Sesion savedSesion = sesionRepository.save(sesion);
        return SesionMapper.mapToSesionDto(savedSesion);
    }

    @Override
    public SesionDto geSesionById(String idSesion) {
        Sesion sesion = sesionRepository.findById(idSesion)
                .orElseThrow(() -> new ResourceNotFoundException("La sesión no existe. ID: "+ idSesion));
        return SesionMapper.mapToSesionDto(sesion);
    }

    @Override
    public List<SesionDto> getSesionesByTaller(String idTallerDiplomado) {
        TallerDiplomado taller = tallerDiplomadoRepository.findById(idTallerDiplomado)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Taller/Diplomado no encontrado. ID: " + idTallerDiplomado));

        List<Sesion> sesiones = sesionRepository.findByTallerDiplomado(taller);

        return sesiones.stream()
                .map(SesionMapper::mapToSesionDto)
                .collect(Collectors.toList());
    }

}
