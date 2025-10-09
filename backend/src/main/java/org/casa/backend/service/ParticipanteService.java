package org.casa.backend.service;

import java.util.List;

import org.casa.backend.dto.ParticipanteDto;
import org.springframework.stereotype.Service;

@Service
public interface ParticipanteService {
    ParticipanteDto createParticipante(ParticipanteDto participanteDto);
    ParticipanteDto getParticipanteById(String idParticipante);
    List<ParticipanteDto> getAllParticipantes();
    ParticipanteDto updateParticipante(String idParticipante, ParticipanteDto participanteDto);
    void deleteParticipante(String idParticipante);
}
