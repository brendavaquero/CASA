package org.casa.backend.service.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.casa.backend.dto.ParticipanteDto;
import org.casa.backend.dto.RegistroPostalDto;
import org.casa.backend.entity.Participante;
import org.casa.backend.enums.Rol;
import org.casa.backend.entity.Usuario;
import org.casa.backend.exception.ResourceNotFoundException;
import org.casa.backend.mapper.ParticipanteMapper;
import org.casa.backend.repository.ParticipanteRepository;
import org.casa.backend.service.ParticipanteService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ParticipanteServiceImpl implements ParticipanteService {

    private final ParticipanteRepository participanteRepository;
    private PasswordEncoder passwordEncoder;

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

    @Override
    public ParticipanteDto createParticipantePublico(ParticipanteDto dto) {
        dto.setRol(Rol.PARTICIPANTE);
        dto.setActivo(true);

        Usuario usuario = ParticipanteMapper.mapToParticipante(dto);
        usuario.setContrasenia(passwordEncoder.encode(dto.getContrasenia()));

        Participante saved = participanteRepository.save((Participante) usuario);
        return ParticipanteMapper.mapToParticipanteDto(saved);
    }

    @Override
    public Participante registrarParticipantePostal(RegistroPostalDto dto) {

        String contraseniaTemporal = generarContraseniaTemporal();
        Participante participante = new Participante();

        // usuario
        participante.setNombre(dto.getNombre());
        participante.setApellidos(dto.getApellidos());
        participante.setCorreo(dto.getCorreo());
        participante.setRol(Rol.PARTICIPANTE);
        // encriptar
        participante.setContrasenia(contraseniaTemporal);

        // participante
        participante.setSexo(dto.getSexo());
        participante.setFechaNacimiento(dto.getFechaNacimiento());
        participante.setCurp(dto.getCurp());
        participante.setNumeroTelefono(dto.getNumeroTelefono());
        participante.setCodigoPostal(dto.getCodigoPostal());
        participante.setPais(dto.getPais());
        participante.setEstado(dto.getEstado());
        participante.setMunicipio(dto.getMunicipio());
        participante.setGradoEstudio(dto.getGradoEstudio());
        participante.setOcupacion(dto.getOcupacion());
        participante.setLenguaIndigena(dto.getLenguaIndigena());
        participante.setSeudonimo(dto.getSeudonimo());

        Participante guardado = participanteRepository.save(participante);

        // Envío de correo PENDIENTE

        return guardado;
    }

    private static String generarContraseniaTemporal() {
        return UUID.randomUUID()
                .toString()
                .replace("-", "")
                .substring(0, 10);
    }
}
