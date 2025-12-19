package org.casa.backend.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.casa.backend.dto.ConvocatoriaResidenciaDto;
import org.casa.backend.entity.ConvocatoriaResidencia;
import org.casa.backend.exception.ResourceNotFoundException;
import org.casa.backend.mapper.ConvocatoriaResidenciaMapper;
import org.casa.backend.repository.ConvocatoriaResidenciaRepository;
import org.casa.backend.service.ConvocatoriaResidenciaService;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ConvocatoriaResidenciaServiceImpl implements ConvocatoriaResidenciaService {

    private ConvocatoriaResidenciaRepository convocatoriaResidenciaRepository;

    @Override
    public ConvocatoriaResidenciaDto createConvocatoriaResi(ConvocatoriaResidenciaDto convocatoriaResiDto) {
        ConvocatoriaResidencia convocatoriaResidencia = ConvocatoriaResidenciaMapper.mapConvocatoriaResidencia(convocatoriaResiDto);
        ConvocatoriaResidencia savedConvocatoriaResi = convocatoriaResidenciaRepository.save(convocatoriaResidencia);
        return ConvocatoriaResidenciaMapper.mapToConvocatoriaResidenciaDto(savedConvocatoriaResi);
    }

    @Override
    public ConvocatoriaResidenciaDto getConvocatoriaResiById(String idActividad) {
        ConvocatoriaResidencia convocatoriaResi = convocatoriaResidenciaRepository.findById(idActividad)
                .orElseThrow(() -> new ResourceNotFoundException("Convocatoria/residencia no encontrada con ID: "+idActividad));
        return ConvocatoriaResidenciaMapper.mapToConvocatoriaResidenciaDto(convocatoriaResi);
    }

    @Override
    public List<ConvocatoriaResidenciaDto> getAllConvocatoriaResi() {
        List<ConvocatoriaResidencia> convocatoriasResis = convocatoriaResidenciaRepository.findAll();
        return convocatoriasResis.stream().map((convocatoriaResi) -> ConvocatoriaResidenciaMapper.mapToConvocatoriaResidenciaDto(convocatoriaResi))
                .collect(Collectors.toList());
    }

    @Override
    public ConvocatoriaResidenciaDto updateConvocatoriaResi(String convocatoriaId, ConvocatoriaResidenciaDto updatedCR) {
        ConvocatoriaResidencia convoResi = convocatoriaResidenciaRepository.findById(convocatoriaId).orElseThrow(
                () -> new ResourceNotFoundException("Convocatoria no encontrada ID: "+ convocatoriaId));
        convoResi.setBases(updatedCR.getBases());
        convoResi.setPremio(updatedCR.getPremio());
        convoResi.setConvocantes(updatedCR.getConvocantes());

        ConvocatoriaResidencia updatedConvoResiObj = convocatoriaResidenciaRepository.save(convoResi);
        return ConvocatoriaResidenciaMapper.mapToConvocatoriaResidenciaDto(updatedConvoResiObj);
    }

}