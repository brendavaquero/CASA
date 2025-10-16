package org.casa.backend.service;

import org.casa.backend.dto.TallerDiplomadoDto;
import org.casa.backend.dto.UsuarioDto;

import java.util.List;

public interface TallerDiplomadoService {
    TallerDiplomadoDto createTallerDiplomado(TallerDiplomadoDto tallerDiplomadoDto);
    TallerDiplomadoDto getTallerDiplomadoById(String idActividad);
    List<TallerDiplomadoDto> getAllTalleresDiplomados();
}
