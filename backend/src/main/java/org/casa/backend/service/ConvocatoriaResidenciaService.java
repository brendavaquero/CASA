package org.casa.backend.service;

import java.util.List;

import org.casa.backend.dto.ConvocatoriaResidenciaDto;
import org.springframework.stereotype.Service;

@Service
public interface ConvocatoriaResidenciaService {
    ConvocatoriaResidenciaDto createConvocatoriaResi(ConvocatoriaResidenciaDto convocatoriaResiDto);
    ConvocatoriaResidenciaDto getConvocatoriaResiById(String idActividad);
    List<ConvocatoriaResidenciaDto> getAllConvocatoriaResi();
    ConvocatoriaResidenciaDto updateConvocatoriaResi(String convocatoriaId, ConvocatoriaResidenciaDto updatedCR);

}