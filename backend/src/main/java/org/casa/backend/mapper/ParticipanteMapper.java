package org.casa.backend.mapper;

import org.casa.backend.dto.ParticipanteDto;
import org.casa.backend.entity.Participante;

public class ParticipanteMapper {

    public static ParticipanteDto mapToParticipanteDto(Participante participante) {
        if (participante == null) {
            return null;
        }

        ParticipanteDto dto = new ParticipanteDto();

        dto.setIdUsuario(participante.getIdUsuario());
        dto.setNombre(participante.getNombre());
        dto.setApellidos(participante.getApellidos());
        dto.setCorreo(participante.getCorreo());
        dto.setContrasenia(participante.getContrasenia());
        dto.setRol(participante.getRol());
        dto.setActivo(participante.isActivo());
        dto.setFecha_registro(participante.getFecha_registro());
        dto.setUltimoAcceso(participante.getUltimo_acceso());
        dto.setNombreParticipante(participante.getNombreParticipante());
        dto.setSexo(participante.getSexo());
        dto.setFechaNacimiento(participante.getFechaNacimiento());
        dto.setCurp(participante.getCurp());
        dto.setNumeroTelefono(participante.getNumeroTelefono());
        dto.setCodigoPostal(participante.getCodigoPostal());
        dto.setPais(participante.getPais());
        dto.setEstado(participante.getEstado());
        dto.setMunicipio(participante.getMunicipio());
        dto.setGradoEstudio(participante.getGradoEstudio());
        dto.setOcupacion(participante.getOcupacion());
        dto.setLenguaIndigena(participante.getLenguaIndigena());
        dto.setSeudonimo(participante.getSeudonimo());

        return dto;
    }


    public static Participante mapToParticipante(ParticipanteDto dto) {
        if (dto == null) {
            return null;
        }

        Participante participante = new Participante();
        participante.setIdUsuario(dto.getIdUsuario());
        participante.setNombre(dto.getNombre());
        participante.setApellidos(dto.getApellidos());
        participante.setCorreo(dto.getCorreo());
        participante.setContrasenia(dto.getContrasenia());
        participante.setRol(dto.getRol());

        participante.setNombreParticipante(dto.getNombreParticipante());
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

        return participante;
    }
}
