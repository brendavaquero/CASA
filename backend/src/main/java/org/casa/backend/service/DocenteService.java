package org.casa.backend.service;

import java.util.List;

import org.casa.backend.dto.DocenteDto;
import org.springframework.stereotype.Service;

@Service
public interface DocenteService {
    DocenteDto createDocente(DocenteDto docenteDto);
    DocenteDto getDocenteById(String idDocente);
    List<DocenteDto> getAllDocentes();
    DocenteDto updateDocente(String idParticipante, DocenteDto docenteDto);
    void deleteDocente(String idDocente);

}
