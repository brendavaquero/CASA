package org.casa.backend.mapper;

import org.casa.backend.dto.ProgramaDto;
import org.casa.backend.entity.Programa;
import org.casa.backend.entity.Usuario;
import org.casa.backend.repository.UsuarioRepository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ProgramaMapper {
    public static Programa mapToPrograma(ProgramaDto dto, UsuarioRepository usuarioRepository) {
        Programa programa = new Programa();
        programa.setIdPrograma(dto.getIdPrograma());
        programa.setNombre(dto.getNombre());
        programa.setDescripcion(dto.getDescripcion());

        // mapear responsables
        if (dto.getResponsablesIds() != null) {
            List<Usuario> responsables = dto.getResponsablesIds().stream()
                    .map(id -> usuarioRepository.findById(id).orElse(null))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
            programa.setResponsables(responsables);
        }

        return programa;
    }

    public static ProgramaDto mapProgramaToDto(Programa programa) {
        ProgramaDto dto = new ProgramaDto();
        dto.setIdPrograma(programa.getIdPrograma());
        dto.setNombre(programa.getNombre());
        dto.setDescripcion(programa.getDescripcion());

        if (programa.getResponsables() != null) {
            List<String> responsablesIds = programa.getResponsables().stream()
                    .map(Usuario::getIdUsuario)
                    .collect(Collectors.toList());
            dto.setResponsablesIds(responsablesIds);
        }

        return dto;
    }
}
