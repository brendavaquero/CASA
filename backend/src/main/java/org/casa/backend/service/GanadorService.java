package org.casa.backend.service;

import java.util.List;

import org.casa.backend.dto.FinalistaDto;
import org.casa.backend.dto.GanadorConvocatoriaDto;
import org.casa.backend.dto.GanadorDto;
import org.casa.backend.entity.Ganador;
import org.springframework.web.multipart.MultipartFile;

public interface GanadorService {
    List<GanadorDto> getAllGanadores();
    GanadorDto createGanador(GanadorDto ganadorDto);
    GanadorDto getGanadorById(String idGanador);
    GanadorDto updateGanador(String idGanador, GanadorDto ganadorDto);
    //Ganador crearGanadorDesdeFinalista(FinalistaDto dto);
    //Ganador confirmarGanador(FinalistaDto dto);
    void seleccionarGanador(String idResultado);
    String uploadImagen(MultipartFile file, String idGanador);
    List<GanadorConvocatoriaDto> obtenerGanadoresPorConvocatoria(String idConvocatoria);
}
