package org.casa.backend.service.impl;

import lombok.AllArgsConstructor;
import org.casa.backend.dto.TallerDiplomadoDto;
import org.casa.backend.entity.TallerDiplomado;
import org.casa.backend.entity.Usuario;
import org.casa.backend.exception.ResourceNotFoundException;
import org.casa.backend.mapper.TallerDiplomadoMapper;
import org.casa.backend.mapper.UsuarioMapper;
import org.casa.backend.repository.TallerDiplomadoRepository;
import org.casa.backend.service.TallerDiplomadoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TallerDiplomadoServiceImpl implements TallerDiplomadoService {

    private TallerDiplomadoRepository tallerDiplomadoRepository;
    @Override
    public TallerDiplomadoDto createTallerDiplomado(TallerDiplomadoDto tallerDiplomadoDto) {
        TallerDiplomado tallerDiplomado = TallerDiplomadoMapper.mapToTallerDiplomado(tallerDiplomadoDto);
        TallerDiplomado savedTallerDiplomado = tallerDiplomadoRepository.save(tallerDiplomado);
        return null;

    }

    @Override
    public TallerDiplomadoDto getTallerDiplomadoById(String idActividad) {
        TallerDiplomado tallerDiplomado = tallerDiplomadoRepository.findById(idActividad)
                .orElseThrow(() -> new ResourceNotFoundException("Taller/diplomado no encontrado. ID: " + idActividad));
        return TallerDiplomadoMapper.mapToTallerDiplomadoDto(tallerDiplomado);
    }

    @Override
    public List<TallerDiplomadoDto> getAllTalleresDiplomados() {
        List<TallerDiplomado> talleresDiplomados =  tallerDiplomadoRepository.findAll();
        return talleresDiplomados.stream().map((tallerDiplomado) -> TallerDiplomadoMapper.mapToTallerDiplomadoDto(tallerDiplomado))
                .collect(Collectors.toList());
    }
}
