package org.casa.backend.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.casa.backend.dto.ProgramaDto;
import org.casa.backend.entity.Programa;
import org.casa.backend.entity.Usuario;
import org.casa.backend.exception.ResourceNotFoundException;
import org.casa.backend.mapper.ProgramaMapper;
import org.casa.backend.repository.ProgramaRepository;
import org.casa.backend.repository.TallerDiplomadoRepository;
import org.casa.backend.repository.UsuarioRepository;
import org.casa.backend.service.ProgramaService;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProgramaServiceImpl implements ProgramaService {

    private ProgramaRepository programaRepository;
    private TallerDiplomadoRepository tallerDiplomadoRepository;
    private UsuarioRepository usuarioRepository;

    @Override
    public List<ProgramaDto> getAllProgramas() {
        return programaRepository.findAll()
                .stream()
                .map(ProgramaMapper::mapProgramaToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProgramaDto getById(String id) {
        return programaRepository.findById(id)
                .map(ProgramaMapper::mapProgramaToDto)
                .orElse(null);
    }

    @Override
    public ProgramaDto createPrograma(ProgramaDto dto) {
        Programa programa = ProgramaMapper.mapToPrograma(dto, usuarioRepository);
        Programa savedPrograma = programaRepository.save(programa);
        return ProgramaMapper.mapProgramaToDto(savedPrograma);
    }

    @Override
    public void deletePrograma(String id) {
        programaRepository.deleteById(id);
    }

    @Override
    public ProgramaDto updatePrograma(String programaId, ProgramaDto updatePrograma) {
        Programa programa = programaRepository.findById(programaId).orElseThrow(
        () -> new ResourceNotFoundException("Programa no encontrado con ese id "+ programaId)
       );
       programa.setNombre(updatePrograma.getNombre()); 
       programa.setDescripcion(updatePrograma.getDescripcion());
       List<Usuario> responsables = usuarioRepository.findAllById(updatePrograma.getResponsablesIds());
        programa.setResponsables(responsables);

        Programa updated = programaRepository.save(programa);

        return ProgramaMapper.mapProgramaToDto(updated);
    }

}
