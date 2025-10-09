    package org.casa.backend.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.casa.backend.dto.TallerDiplomadoDto;
import org.casa.backend.entity.TallerDiplomado;
import org.casa.backend.mapper.TallerDiplomadoMapper;
import org.casa.backend.repository.TallerDiplomadoRepository;
import org.casa.backend.service.TallerDiplomadoService;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TallerDiplomadoServiceImpl implements TallerDiplomadoService {

    private final TallerDiplomadoRepository tallerRepo;

    @Override
    public List<TallerDiplomadoDto> listarTalleres() {
        return tallerRepo.findAll()
                .stream()
                .map(TallerDiplomadoMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public TallerDiplomadoDto getTallerById(String idActividad) {
        return tallerRepo.findById(idActividad)
                .map(TallerDiplomadoMapper::mapToDto)
                .orElse(null);
    }

    @Override
    public TallerDiplomadoDto createTaller(TallerDiplomadoDto tallerDto) {
        TallerDiplomado taller = new TallerDiplomado();
        taller.setTitulo(tallerDto.getTitulo());
        taller.setDescripcion(tallerDto.getDescripcion());
        taller.setFechaInicio(tallerDto.getFechaInicio());
        taller.setFechaCierre(tallerDto.getFechaCierre());
        taller.setFechaResultados(tallerDto.getFechaResultados());
        if (tallerDto.getFechaCreacion() != null) {
            taller.setFechaCreacion(tallerDto.getFechaCreacion());
        } else {
            taller.setFechaCreacion(LocalDateTime.now());
        }
        taller.setRequisitos(tallerDto.getRequisitos());
        taller.setEstado(tallerDto.getEstado());
        taller.setTipoActividad(tallerDto.getTipoActividad());
        taller.setCupo(tallerDto.getCupo());
        taller.setObjetivoGeneral(tallerDto.getObjetivoGeneral());
        taller.setObjetivosEspecificos(tallerDto.getObjetivosEspecificos());
        taller.setTemas(tallerDto.getTemas());
        taller.setMaterialSol(tallerDto.getMaterialSol());
        taller.setCriteriosSeleccion(tallerDto.getCriteriosSeleccion());
        taller.setNotas(tallerDto.getNotas());
        taller.setNumSesiones(tallerDto.getNumSesiones());

        TallerDiplomado guardado = tallerRepo.save(taller);
        return TallerDiplomadoMapper.mapToDto(guardado);
    }

    @Override
    public void deleteTaller(String idActividad) {
        tallerRepo.deleteById(idActividad);
    }
}
