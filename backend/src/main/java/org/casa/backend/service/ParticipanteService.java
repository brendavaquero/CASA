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
    /*
    private final ParticipanteRepository participanteRepository;
    
    public ParticipanteService(ParticipanteRepository participanteRepository) {
        this.participanteRepository = participanteRepository;
    }
    
    public List<Participante> listarTodos() {
        return participanteRepository.findAll();
    }
    
    public Optional<Participante> obtenerPorId(String id) {
        return participanteRepository.findById(id);
    }
    
    public Optional<Participante> obtenerPorCurp(String curp) {
        return participanteRepository.findByCurp(curp);
    }
    
    public Participante guardar(Participante participante) {
        return participanteRepository.save(participante);
    }
    
    public void eliminar(String id) {
        participanteRepository.deleteById(id);
    }
    
    public boolean existePorCurp(String curp) {
        return participanteRepository.existsByCurp(curp);
    }
*/
}
