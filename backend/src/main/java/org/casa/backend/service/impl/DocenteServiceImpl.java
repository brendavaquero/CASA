package org.casa.backend.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.casa.backend.dto.DocenteDto;
import org.casa.backend.entity.Docente;
import org.casa.backend.exception.ResourceNotFoundException;
import org.casa.backend.mapper.DocenteMapper;
import org.casa.backend.repository.DocenteRepository;
import org.casa.backend.service.DocenteService;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DocenteServiceImpl implements DocenteService{
    private final DocenteRepository docenteRepository;

    @Override
    public DocenteDto createDocente(DocenteDto docenteDto) {
        Docente docente = DocenteMapper.mapToDocente(docenteDto);
        Docente savedDocente = docenteRepository.save(docente);
        return DocenteMapper.mapToDocenteDto(savedDocente);
    }

    @Override
    public DocenteDto getDocenteById(String idDocente) {
        Docente docente = docenteRepository.findById(idDocente)
                .orElseThrow(() -> new ResourceNotFoundException("Docente no encontrado con id: " + idDocente));
        return DocenteMapper.mapToDocenteDto(docente);
    }

    @Override
    public List<DocenteDto> getAllDocentes() {
        List<Docente> docentes = docenteRepository.findAll();
        return docentes.stream()
                .map(DocenteMapper::mapToDocenteDto)
                .collect(Collectors.toList());
    }

    @Override
    public DocenteDto updateDocente(String idParticipante, DocenteDto docenteDto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateDocente'");
    }

    @Override
    public void deleteDocente(String idDocente) {
        Docente docente = docenteRepository.findById(idDocente)
                .orElseThrow(() -> new ResourceNotFoundException("Docente no encontrado con id: " + idDocente));
        docenteRepository.delete(docente);
    }

}
