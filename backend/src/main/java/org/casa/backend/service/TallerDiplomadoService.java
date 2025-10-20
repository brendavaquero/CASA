package org.casa.backend.service;

import org.casa.backend.dto.TallerDiplomadoDto;

import java.util.List;

public interface TallerDiplomadoService {
    TallerDiplomadoDto createTallerDiplomado(TallerDiplomadoDto tallerDiplomadoDto);
    TallerDiplomadoDto getTallerDiplomadoById(String idActividad);
    List<TallerDiplomadoDto> getAllTalleresDiplomados();
    TallerDiplomadoDto updateActividad(String actividadId, TallerDiplomadoDto updatedActividad);
    TallerDiplomadoDto updateTallerDiplo(String tallerId, TallerDiplomadoDto updatedTD);
}
