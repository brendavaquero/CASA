package org.casa.backend.service;

import java.util.List;

import org.casa.backend.dto.JuradoConvocatoriaDto;
import org.casa.backend.dto.JuradoDto;
import org.casa.backend.dto.UsuarioJurado;
import org.casa.backend.entity.Jurado;

public interface JuradoService {
    List<JuradoDto> getAllJurados();
    JuradoDto getJuradoById(String idJurado);
    JuradoDto crear(JuradoDto dto);
    List<JuradoDto> listarPorConvocatoria(String idConvocatoria);
    List<UsuarioJurado> obtenerJuradosPorConvocatoria(String idActividad);
    List<JuradoConvocatoriaDto> obtenerConvocatoriasPorUsuario(String idUsuario);
    Jurado guardarJurado(Jurado jurado);
}
