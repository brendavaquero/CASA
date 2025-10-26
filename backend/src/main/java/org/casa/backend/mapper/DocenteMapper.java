package org.casa.backend.mapper;

import org.casa.backend.dto.DocenteDto;
import org.casa.backend.entity.Docente;

public class DocenteMapper {
     public static DocenteDto mapToDocenteDto(Docente docente) {
        if (docente == null) {
            return null;
        }

        DocenteDto dto = new DocenteDto();

        dto.setIdUsuario(docente.getIdUsuario());
        dto.setNombre(docente.getNombre());
        dto.setApellidos(docente.getApellidos());
        dto.setCorreo(docente.getCorreo());
        dto.setContrasenia(docente.getContrasenia());
        dto.setRol(docente.getRol());
        dto.setActivo(docente.isActivo());
        dto.setFecha_registro(docente.getFecha_registro());
        dto.setUltimoAcceso(docente.getUltimo_acceso());
        dto.setFoto(docente.getFoto());
        dto.setSemblanza(docente.getSemblanza());

        return dto;
    }

    public static Docente mapToDocente(DocenteDto dto) {
        if (dto == null) {
            return null;
        }

        Docente docente = new Docente();
        docente.setIdUsuario(dto.getIdUsuario());
        docente.setNombre(dto.getNombre());
        docente.setApellidos(dto.getApellidos());
        docente.setCorreo(dto.getCorreo());
        docente.setContrasenia(dto.getContrasenia());
        docente.setRol(dto.getRol());

        docente.setFoto(dto.getFoto());
        docente.setSemblanza(dto.getSemblanza());

        return docente;
    }

}
