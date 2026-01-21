package org.casa.backend.service.impl;

import lombok.AllArgsConstructor;
import org.casa.backend.dto.ActaGanadorDto;
import org.casa.backend.entity.*;
import org.casa.backend.repository.ConvocatoriaResidenciaRepository;
import org.casa.backend.repository.GanadorRepository;
import org.casa.backend.repository.PostulacionRepository;
import org.casa.backend.service.ActaService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ActaServiceImpl implements ActaService {

    private final GanadorRepository ganadorRepository;
    private final PostulacionRepository postulacionRepository;
    private final ConvocatoriaResidenciaRepository convocatoriaRepository;


    @Override
    public ActaGanadorDto generarActaPorConvocatoria(String idConvocatoria) {

        Ganador ganador = ganadorRepository
                .findFirstByResultado_IdConvocatoria(idConvocatoria)
                .orElseThrow(() -> new RuntimeException("No existe ganador para la convocatoria"));

        ResultadoRondaUno resultado = ganador.getResultado();

        Postulacion postulacion = postulacionRepository
                .findById(resultado.getIdPostulacion())
                .orElseThrow(() -> new RuntimeException("Postulación no encontrada"));

        ConvocatoriaResidencia convocatoria = convocatoriaRepository
                .findById(idConvocatoria)
                .orElseThrow(() -> new RuntimeException("Convocatoria no encontrada"));

        ActaGanadorDto dto = new ActaGanadorDto();

        // Acta
        dto.setTipo("DICTAMEN");
        dto.setFecha(LocalDate.now());
        dto.setLugar("San Agustín Etla");

        // Convocatoria
        dto.setNombreConvocatoria(convocatoria.getTitulo());
        dto.setConvocantes(convocatoria.getConvocantes());
        dto.setPremio(convocatoria.getPremio());

        // Ganador
        Participante p = postulacion.getParticipante();
        dto.setNombreGanador(p.getNombre() + " " + p.getApellidos());
        dto.setNombreObra(postulacion.getNombreObra());
        dto.setCalificacionFinal(resultado.getPromedio());

        // Jurados
        dto.setJurados(
                convocatoria.getJurados().stream()
                        .map(j -> j.getUsuario().getNombre() + " " + j.getUsuario().getApellidos())
                        .collect(Collectors.toList())
        );

        return dto;
    }
}