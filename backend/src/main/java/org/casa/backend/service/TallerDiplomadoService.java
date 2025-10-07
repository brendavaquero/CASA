package org.casa.backend.service;

import java.util.List;
import org.casa.backend.dto.TallerDiplomadoDto;

public interface TallerDiplomadoService {
    List<TallerDiplomadoDto> listarTalleres();
    TallerDiplomadoDto getTallerById(String idActividad);
    TallerDiplomadoDto createTaller(TallerDiplomadoDto tallerDto);
    void deleteTaller(String idActividad);
}
