package org.casa.backend.service;

import java.util.List;

import org.casa.backend.dto.ActividadDto;

public interface ActividadService {
    List<ActividadDto> listarActividades();
    ActividadDto getActividadById(String idActividad);
    void deleteActividad(String idActividad);
}
