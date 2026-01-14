package org.casa.backend.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.casa.backend.dto.FinalistaDto;
import org.casa.backend.dto.GanadorDto;
import org.casa.backend.entity.Archivo;
import org.casa.backend.entity.Evaluacion;
import org.casa.backend.entity.Ganador;
import org.casa.backend.entity.ResultadoRondaUno;
import org.casa.backend.exception.ResourceNotFoundException;
import org.casa.backend.mapper.GanadorMapper;
import org.casa.backend.repository.ArchivoRepository;
import org.casa.backend.repository.EvaluacionRepository;
import org.casa.backend.repository.GanadorRepository;
import org.casa.backend.repository.ResultadoRondaUnoRepository;
import org.casa.backend.service.GanadorService;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class GanadorServiceImpl implements GanadorService {

    private GanadorRepository ganadorRepository;
    private EvaluacionRepository evaluacionRepository;
    private ArchivoRepository archivoRepository;
    private ResultadoRondaUnoRepository resultadoRepository;

    @Override
    public List<GanadorDto> getAllGanadores() {
        return ganadorRepository.findAll()
                .stream()
                .map(GanadorMapper::mapToGanadorDto)
                .collect(Collectors.toList());
    }

    @Override
    public GanadorDto createGanador(GanadorDto ganadorDto) {
        ResultadoRondaUno resultado = resultadoRepository.findById(ganadorDto.getIdResultado())
                .orElseThrow(() -> new ResourceNotFoundException("evaluacion no encontrada"));

        Archivo archivo = archivoRepository.findById(ganadorDto.getIdArchivo())
                .orElseThrow(() -> new ResourceNotFoundException("archivo no encontrado"));

        Ganador ganador = new Ganador();
        ganador.setResultado(resultado);
        ganador.setSemblanza(ganadorDto.getSemblanza());
        ganador.setFoto(ganadorDto.getFoto());
        ganador.setArchivo(archivo);

        Ganador saved = ganadorRepository.save(ganador);
        return GanadorMapper.mapToGanadorDto(saved);
    }

    @Override
    public GanadorDto getGanadorById(String idGanador) {
        Ganador ganador = ganadorRepository.findById(idGanador)
                .orElseThrow(() -> new ResourceNotFoundException("No existe un ganador con id: " + idGanador));
        return GanadorMapper.mapToGanadorDto(ganador);
    }

    @Override
    public GanadorDto updateGanador(String idGanador, GanadorDto ganadorDto) {
        Ganador ganador = ganadorRepository.findById(idGanador)
                .orElseThrow(() -> new ResourceNotFoundException("No existe un ganador con id: " + idGanador));

        ganador.setSemblanza(ganadorDto.getSemblanza());
        ganador.setFoto(ganadorDto.getFoto());

        Ganador updated = ganadorRepository.save(ganador);

        return GanadorMapper.mapToGanadorDto(updated);
    }

    /*@Override
    public Ganador crearGanadorDesdeFinalista(FinalistaDto dto) {

        Ganador ganador = new Ganador();
        Evaluacion evaluacion = evaluacionRepository
                .getReferenceById(dto.getIdResultado());
        // Archivo a partir de la postulación
        Archivo archivo = archivoRepository
                .findByPostulacion_IdPostulacion(dto.getIdPostulacion())
                .orElseThrow(() ->
                        new IllegalStateException(
                                "No existe archivo para la postulación "
                                        + dto.getIdPostulacion()
                        )
                );
        ganador.setEvaluacion(evaluacion);
        ganador.setArchivo(archivo);
        ganador.setSemblanza(null);
        ganador.setFoto(null);

        return ganadorRepository.save(ganador);
    }*/

    @Override
    public void seleccionarGanador(String idResultado) {
        ResultadoRondaUno resultado = resultadoRepository.findById(idResultado)
                .orElseThrow(() -> new RuntimeException("Resultado no encontrado"));
        Archivo archivo = archivoRepository
                .findByPostulacion_IdPostulacion(resultado.getIdPostulacion())
                .orElseThrow(() -> new RuntimeException("Archivo no encontrado"));

        Ganador ganador = new Ganador();
        ganador.setResultado(resultado);
        ganador.setArchivo(archivo);
        ganador.setSemblanza(null);
        ganador.setFoto(null);
        ganadorRepository.save(ganador);
    }
    //@Override
    //@Transactional
    //public Ganador confirmarGanador(FinalistaDto dto) {

//        ResultadoRondaUno resultado = resultadoRondaUnoRepository
//                .findById(dto.getIdResultado())
//                .orElseThrow(() -> new RuntimeException("Resultado no encontrado"));
//
//        Evaluacion evaluacion = resultado.getEvaluacion();
//
//        Archivo archivo = archivoRepository
//                .findByPostulacion_IdPostulacion(dto.getIdPostulacion())
//                .orElseThrow(() -> new RuntimeException("Archivo no encontrado"));
//
//        // (opcional pero recomendado)
//        if (ganadorRepository.existsByEvaluacion_IdEvaluacion(
//                evaluacion.getIdEvaluacion())) {
//            throw new IllegalStateException("Esta evaluación ya tiene un ganador");
//        }
//
//        Ganador ganador = new Ganador();
//        ganador.setEvaluacion(evaluacion);
//        ganador.setArchivo(archivo);
//        ganador.setFoto(null);
//        ganador.setSemblanza(null);
//
//        return ganadorRepository.save(ganador);
    //}

}
