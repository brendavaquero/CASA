package org.casa.backend.service.impl;

import lombok.AllArgsConstructor;
import org.casa.backend.dto.TallerDiplomadoDto;
import org.casa.backend.entity.TallerDiplomado;
import org.casa.backend.exception.ResourceNotFoundException;
import org.casa.backend.mapper.TallerDiplomadoMapper;
import org.casa.backend.repository.TallerDiplomadoRepositorio;
import org.casa.backend.service.TallerDiplomadoService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TallerDiplomadoServiceImpl implements TallerDiplomadoService {

    private TallerDiplomadoRepositorio tallerDiplomadoRepositorio;
    @Override
    public TallerDiplomadoDto createTallerDiplomado(TallerDiplomadoDto tallerDiplomadoDto) {
        TallerDiplomado tallerDiplomado = TallerDiplomadoMapper.mapToTallerDiplomado(tallerDiplomadoDto);
        TallerDiplomado savedTallerDiplomado = tallerDiplomadoRepositorio.save(tallerDiplomado);
        return null;

    }

    @Override
    public TallerDiplomadoDto getTallerDiplomadoById(String idActividad) {
        TallerDiplomado tallerDiplomado = tallerDiplomadoRepositorio.findById(idActividad)
                .orElseThrow(() -> new ResourceNotFoundException("Taller/diplomado no encontrado. ID: " + idActividad));
        return TallerDiplomadoMapper.mapToTallerDiplomadoDto(tallerDiplomado);
    }
}
