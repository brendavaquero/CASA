package org.casa.backend.service;

import java.util.List;

import org.casa.backend.dto.GanadorDto;

public interface GanadorService {
    List<GanadorDto> getAllGanadores();
    GanadorDto createGanador(GanadorDto ganadorDto);
    GanadorDto getGanadorById(String idGanador);
    GanadorDto updateGanador(String idGanador, GanadorDto ganadorDto);
}
