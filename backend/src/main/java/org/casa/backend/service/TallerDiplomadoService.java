package org.casa.backend.service;

import org.casa.backend.dto.TallerDiplomadoDto;

public interface TallerDiplomadoService {
    TallerDiplomadoDto createTallerDiplomado(TallerDiplomadoDto tallerDiplomadoDto);
    TallerDiplomadoDto getTallerDiplomadoById(String idActividad);
}

