package org.casa.backend.service;

import java.util.List;

import org.casa.backend.dto.ObraDto;

public interface ObraService {
    List<ObraDto> getAllObras();
    ObraDto getObraById(String idObra);
    ObraDto crear(ObraDto obraDto);
    ObraDto updateObra(String idObra,ObraDto updatedObra);
}
