package org.casa.backend.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.casa.backend.dto.ObraDto;
import org.casa.backend.entity.Obra;
import org.casa.backend.exception.ResourceNotFoundException;
import org.casa.backend.mapper.ObraMapper;
import org.casa.backend.repository.ArchivoRepository;
import org.casa.backend.repository.GanadorRepository;
import org.casa.backend.repository.ObraRepository;
import org.casa.backend.service.ObraService;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ObraServiceImpl implements ObraService {
    private ObraRepository obraRepository;
    private GanadorRepository ganadorRepository;
    private ArchivoRepository archivoRepository;

    
    @Override
    public List<ObraDto> getAllObras() {
        return obraRepository.findAll()
            .stream()
            .map(ObraMapper::mapToDto)
            .collect(Collectors.toList());
    }

    @Override
    public ObraDto getObraById(String idObra) {
        Obra obra = obraRepository.findById(idObra)
            .orElseThrow(() -> new ResourceNotFoundException("Obra no encontrada con id: "+idObra));
        return ObraMapper.mapToDto(obra);
    }

    @Override
    public ObraDto crear(ObraDto obraDto) {
        Obra obra = new Obra();
        obra.setGanador(
            ganadorRepository.findById(obraDto.getIdGanador()).orElseThrow()
        );

        obra.setArchivo(
            archivoRepository.findById(obraDto.getIdArchivo()).orElseThrow()
        );

        obra.setComentarios(obraDto.getComentarios());
        obra.setVersionActual(obraDto.isVersionActual());
        return ObraMapper.mapToDto(obraRepository.save(obra));
    }

    @Override
    public ObraDto updateObra(String idObra, ObraDto updatedObra) {
        Obra obra = obraRepository.findById(idObra)
            .orElseThrow(() -> new ResourceNotFoundException("Obra no encontrada"));
        
        obra.setComentarios(updatedObra.getComentarios());
        obra.setVersionActual(updatedObra.isVersionActual());

        Obra updated = obraRepository.save(obra);
        return ObraMapper.mapToDto(updated);
    }
    
}
