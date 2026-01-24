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
import org.springframework.dao.DataIntegrityViolationException;
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
        if (participanteRepository.existsByCurp(participanteDto.getCurp())) {
            // Lanzamos una excepción controlada o retornamos un error
            throw new IllegalArgumentException("La CURP ya está registrada");
        }
        Participante participante = ParticipanteMapper.mapToParticipante(participanteDto);
        participante.setRol(Rol.PARTICIPANTE);
        Participante savedParticipante = participanteRepository.save(participante);
        return ParticipanteMapper.mapToParticipanteDto(savedParticipante);
    }

    @Override
    public ParticipanteDto createParticipantePublico(ParticipanteDto dto) {
        if (participanteRepository.existsByCurp(dto.getCurp())) {
            // Lanzamos una excepción controlada o retornamos un error
            throw new IllegalArgumentException("La CURP ya está registrada");
        }
        Participante participante = ParticipanteMapper.mapToParticipante(dto);
        participante.setRol(Rol.PARTICIPANTE);
        participante.setActivo(true);
        //Usuario usuario = ParticipanteMapper.mapToParticipante(dto);
        participante.setContrasenia(passwordEncoder.encode(dto.getContrasenia()));

        try {
            // Intentamos guardar
            Participante saved = participanteRepository.save(participante);
            return ParticipanteMapper.mapToParticipanteDto(saved);
        } catch (DataIntegrityViolationException e) {
            // Captura cualquier violación de la restricción UNIQUE en la BD
            throw new IllegalArgumentException("La CURP ya está asociada a un usuario");
        }
    }

    public void registrarParticipante(Participante participante) {
        boolean existe = participanteRepository.existsByCurp(participante.getCurp());
        if (existe) {
            throw new IllegalArgumentException("El CURP ya está registrado");
        }
        participanteRepository.save(participante);
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
        participante.setCorreo(participanteDto.getCorreo());
        participante.setNumeroTelefono(participanteDto.getNumeroTelefono());
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
    public Participante registrarParticipantePostal(RegistroPostalDto dto) {

        String contraseniaTemporal = generarContraseniaTemporal();
        Participante participante = new Participante();

        // usuario
        participante.setNombre(dto.getNombre());
        participante.setApellidos(dto.getApellidos());
        participante.setCorreo(dto.getCorreo());
        participante.setRol(Rol.PARTICIPANTE);
        // encriptar
        //participante.setContrasenia(contraseniaTemporal);
        participante.setContrasenia(passwordEncoder.encode(contraseniaTemporal));
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
