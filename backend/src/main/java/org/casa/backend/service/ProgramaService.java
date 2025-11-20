package org.casa.backend.service;

import java.util.List;
import org.casa.backend.dto.ProgramaDto;
import org.springframework.stereotype.Service;

@Service
public interface ProgramaService {
    List<ProgramaDto> getAllProgramas();
    ProgramaDto getById(String id);
    ProgramaDto createPrograma(ProgramaDto dto);
    void deletePrograma(String id);
    ProgramaDto updatePrograma(String programaId, ProgramaDto updatePrograma);
} 