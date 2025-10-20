package org.casa.backend.service;

import org.casa.backend.dto.SesionDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SesionService {
    SesionDto createSesion(SesionDto sesionDto);
    SesionDto geSesionById(String idSesion);
    List<SesionDto> getSesionesByTaller(String idTallerDiplomado);
    SesionDto updateSesion(String sesionId, SesionDto updatedSesion);
}
