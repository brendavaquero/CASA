package org.casa.backend.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.casa.backend.dto.ParticipanteDto;
import org.casa.backend.entity.Participante;
import org.casa.backend.exception.ResourceNotFoundException;
import org.casa.backend.mapper.ParticipanteMapper;
import org.casa.backend.repository.ParticipanteRepository;
import org.casa.backend.service.ParticipanteService;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ParticipanteServiceImpl implements ParticipanteService {

    private final ParticipanteRepository participanteRepository;

    @Override
    public ParticipanteDto createParticipante(ParticipanteDto participanteDto) {
        Participante participante = ParticipanteMapper.mapToParticipante(participanteDto);
        Participante savedParticipante = participanteRepository.save(participante);
        return ParticipanteMapper.mapToParticipanteDto(savedParticipante);
    }

    @Override
    public List<ParticipanteDto> getAllParticipantes() {
        List<Participante> participantes = participanteRepository.findAll();
        return participantes.stream()
                .map(ParticipanteMapper::mapToParticipanteDto)
                .collect(Collectors.toList());
    }

    @Override
    public ParticipanteDto getParticipanteById(String idParticipante) {
        Participante participante = participanteRepository.findById(idParticipante)
                .orElseThrow(() -> new ResourceNotFoundException("Participante no encontrado con id: " + idParticipante));
        return ParticipanteMapper.mapToParticipanteDto(participante);
    }

    @Override
    public ParticipanteDto updateParticipante(String idParticipante, ParticipanteDto participanteDto) {
        Participante participante = participanteRepository.findById(idParticipante)
                .orElseThrow(() -> new ResourceNotFoundException("Participante no encontrado con id: " + idParticipante));

        participante.setNombre(participanteDto.getNombre());
        participante.setApellidos(participanteDto.getApellidos());
        participante.setActivo(participanteDto.getActivo());
        participante.setUltimo_acceso(participanteDto.getUltimoAcceso());
        participante.setNombreParticipante(participanteDto.getNombreParticipante());
        participante.setNumeroTelefono(participanteDto.getNumeroTelefono());
        participante.setCodigoPostal(participanteDto.getCodigoPostal());
        participante.setPais(participanteDto.getPais());
        participante.setEstado(participanteDto.getEstado());
        participante.setMunicipio(participanteDto.getMunicipio());
        participante.setGradoEstudio(participanteDto.getGradoEstudio());
        participante.setOcupacion(participanteDto.getOcupacion());
        participante.setSeudonimo(participanteDto.getSeudonimo());

        Participante updatedParticipante = participanteRepository.save(participante);
        return ParticipanteMapper.mapToParticipanteDto(updatedParticipante);
    }

    @Override
    public void deleteParticipante(String idParticipante) {
        Participante participante = participanteRepository.findById(idParticipante)
                .orElseThrow(() -> new ResourceNotFoundException("Participante no encontrado con id: " + idParticipante));
        participanteRepository.delete(participante);
    }
}
