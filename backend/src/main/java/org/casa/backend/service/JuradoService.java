package org.casa.backend.service;

import java.util.List;

import org.casa.backend.dto.JuradoDto;

public interface JuradoService {
    List<JuradoDto> getAllJurados();
    JuradoDto getJuradoById(String idJurado);
    JuradoDto crear(JuradoDto dto);
    List<JuradoDto> listarPorConvocatoria(String idConvocatoria);
}
