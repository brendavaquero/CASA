package org.casa.backend.service;

import java.util.List;

import org.casa.backend.dto.ConvocatoriaResidenciaDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface ConvocatoriaResidenciaService {
    ConvocatoriaResidenciaDto createConvocatoriaResi(ConvocatoriaResidenciaDto convocatoriaResiDto, MultipartFile imagen,MultipartFile bases);
    ConvocatoriaResidenciaDto getConvocatoriaResiById(String idActividad);
    List<ConvocatoriaResidenciaDto> getAllConvocatoriaResi();
    ConvocatoriaResidenciaDto updateConvocatoriaResi(String convocatoriaId, ConvocatoriaResidenciaDto updatedCR);

}
