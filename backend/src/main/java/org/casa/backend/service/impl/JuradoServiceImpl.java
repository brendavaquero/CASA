package org.casa.backend.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.casa.backend.dto.JuradoConvocatoriaDto;
import org.casa.backend.dto.JuradoDto;
import org.casa.backend.entity.Jurado;
import org.casa.backend.enums.EstadoActividad;
import org.casa.backend.enums.TipoActividad;
import org.casa.backend.dto.UsuarioJurado;
import org.casa.backend.exception.ResourceNotFoundException;
import org.casa.backend.mapper.JuradoMapper;
import org.casa.backend.repository.ConvocatoriaResidenciaRepository;
import org.casa.backend.repository.JuradoRepository;
import org.casa.backend.repository.UsuarioRepository;
import org.casa.backend.service.JuradoService;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class JuradoServiceImpl implements JuradoService {
    private  JuradoRepository repository;
    private  UsuarioRepository usuarioRepo;
    private  ConvocatoriaResidenciaRepository convocatoriaRepo;

//    @Override
//    public JuradoDto crear(JuradoDto dto) {
//        Jurado jurado = new Jurado();
//        jurado.setUsuario(
//            usuarioRepo.findById(dto.getIdUsuario()).orElseThrow()
//        );
//        jurado.setConvocatoria(
//            convocatoriaRepo.findById(dto.getIdConvocatoria()).orElseThrow()
//        );
//        return JuradoMapper.mapToDto(repository.save(jurado));
//    }

    @Override
    public JuradoDto crear(JuradoDto dto) {

        Jurado jurado = new Jurado();
        jurado.setUsuario(
                usuarioRepo.findById(dto.getIdUsuario())
                        .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"))
        );
        jurado.setConvocatoria(
                convocatoriaRepo.findById(dto.getIdConvocatoria())
                        .orElseThrow(() -> new ResourceNotFoundException("Convocatoria no encontrada"))
        );

        // VALIDACIÓN DE NEGOCIO
        long totalJurados = repository
                .countByConvocatoria_IdActividad(dto.getIdConvocatoria());

        if (totalJurados >= 5) {
            throw new IllegalStateException(
                    "La convocatoria ya cuenta con el máximo de 5 jurados"
            );
        }

        return JuradoMapper.mapToDto(repository.save(jurado));
    }


    @Override
    public List<JuradoDto> listarPorConvocatoria(String idConvocatoria) {
        return repository.findById(idConvocatoria)
                .stream()
                .map(JuradoMapper::mapToDto)
                .toList();
    }

    @Override
    public List<JuradoDto> getAllJurados() {
        return repository.findAll()
                .stream()
                .map(JuradoMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public JuradoDto getJuradoById(String idJurado) {
        Jurado jurado = repository.findById(idJurado)
                .orElseThrow(() -> new ResourceNotFoundException("Jurado no encontrado con id: " + idJurado));
        return JuradoMapper.mapToDto(jurado);
    }

    @Override
    public List<UsuarioJurado> obtenerJuradosPorConvocatoria(String idActividad) {
        return repository.findByConvocatoriaIdActividad(idActividad)
            .stream()
            .map(j -> new UsuarioJurado(
                    j.getIdJurado(),
                    j.getUsuario().getIdUsuario(),
                    j.getConvocatoria().getIdActividad(),
                    j.getUsuario().getNombre(),
                    j.getUsuario().getApellidos(),
                    j.getUsuario().getCorreo(),
                    j.getUsuario().getContrasenia(),
                    j.getUsuario().getRol()
            ))
            .toList();
    }
    @Override
    public List<JuradoConvocatoriaDto> obtenerConvocatoriasPorUsuario(String idUsuario) {
       return repository.findConvocatoriasByUsuario(idUsuario);
    }

    @Override
    public Jurado guardarJurado(Jurado jurado) {

        String idConvocatoria = jurado.getConvocatoria().getIdActividad();

        long totalJurados = repository
                .countByConvocatoria_IdActividad(idConvocatoria);

        if (totalJurados >= 5) {
            throw new IllegalStateException(
                    "La convocatoria ya cuenta con el máximo de 5 jurados"
            );
        }
        return repository.save(jurado);
    }
}
