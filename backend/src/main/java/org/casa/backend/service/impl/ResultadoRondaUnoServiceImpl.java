package org.casa.backend.service.impl;

import lombok.AllArgsConstructor;
import org.casa.backend.dto.FinalistaDto;
import org.casa.backend.entity.Postulacion;
import org.casa.backend.entity.ResultadoRondaUno;
import org.casa.backend.repository.EvaluacionRepository;
import org.casa.backend.repository.PostulacionRepository;
import org.casa.backend.repository.ResultadoRondaUnoRepository;
import org.casa.backend.repository.projection.PromedioPostulacionProjection;
import org.casa.backend.service.ResultadoRondaUnoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor

public class ResultadoRondaUnoServiceImpl implements ResultadoRondaUnoService {
    private EvaluacionRepository evaluacionRepository;
    private PostulacionRepository postulacionRepository;
    private ResultadoRondaUnoRepository resultadoRepository;

    @Override
    @Transactional
    public void cerrarRondaUno(String idConvocatoria) {

        // 1 Evitar doble cierre
        if (resultadoRepository.existsByIdConvocatoria(idConvocatoria)) {
            throw new IllegalStateException(
                    "La ronda uno ya fue cerrada para esta convocatoria"
            );
        }

        // 2 Calcular promedios
        List<PromedioPostulacionProjection> promedios =
                evaluacionRepository.calcularPromediosRondaUno(idConvocatoria);

        if (promedios.isEmpty()) {
            throw new IllegalStateException(
                    "No existen evaluaciones válidas para cerrar la ronda"
            );
        }

        // 3 Obtener fecha de postulación (para desempate)
        Map<String, LocalDateTime> fechaPostulacionMap =
                postulacionRepository.findByActividad_IdActividad(idConvocatoria)
                        .stream()
                        .collect(Collectors.toMap(
                                Postulacion::getIdPostulacion,
                                Postulacion::getFechaPostulacion
                        ));

        // 4️ Ordenar según reglas
//        List<PromedioPostulacionProjection> ordenados = promedios.stream()
//                .sorted(Comparator
//                        .comparing(PromedioPostulacionProjection::getPromedio).reversed()
//                        .thenComparing(PromedioPostulacionProjection::getTotalEvaluaciones).reversed()
//                        .thenComparing(p ->
//                                fechaPostulacionMap.get(p.getIdPostulacion())
//                        )
//                )
//                .toList();

        List<PromedioPostulacionProjection> ordenados = promedios.stream()
                .sorted(
                        Comparator
                                .comparing(
                                        PromedioPostulacionProjection::getPromedio,
                                        Comparator.reverseOrder()
                                )
                                .thenComparing(
                                        PromedioPostulacionProjection::getTotalEvaluaciones,
                                        Comparator.reverseOrder()
                                )
                                .thenComparing(p ->
                                        fechaPostulacionMap.get(p.getIdPostulacion())
                                )
                )
                .toList();


        // 5 Persistir resultados
        int posicion = 1;

        for (PromedioPostulacionProjection p : ordenados) {

            ResultadoRondaUno resultado = new ResultadoRondaUno();
            resultado.setIdConvocatoria(idConvocatoria);
            resultado.setIdPostulacion(p.getIdPostulacion());
            resultado.setPromedio(BigDecimal.valueOf(p.getPromedio()));
            resultado.setTotalEvaluaciones(p.getTotalEvaluaciones().intValue());
            resultado.setPosicion(posicion);
            resultado.setFinalista(posicion <= 5);
            resultado.setMencionHonorifica(false);

            resultadoRepository.save(resultado);
            posicion++;
        }
    }

    @Override
    public List<ResultadoRondaUno> obtenerResultados(String idConvocatoria) {
        return resultadoRepository
                .findByIdConvocatoriaOrderByPosicionAsc(idConvocatoria);
    }

    @Override
    public List<ResultadoRondaUno> obtenerFinalistas(String idConvocatoria) {
        return resultadoRepository
                .findByIdConvocatoriaAndFinalistaTrueOrderByPosicionAsc(idConvocatoria);
    }

    @Override
    public List<FinalistaDto> obtenerFinalistasDTO(String idConvocatoria) {
        return resultadoRepository.obtenerFinalistasDTO(idConvocatoria);
    }
}
