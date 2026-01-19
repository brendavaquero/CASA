package org.casa.backend.repository;

import java.util.List;

import org.casa.backend.dto.EvaluacionDto;
import org.casa.backend.entity.Evaluacion;
import org.casa.backend.repository.projection.PromedioPostulacionProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EvaluacionRepository extends JpaRepository<Evaluacion, String>{
    boolean existsByJurado_IdJuradoAndPostulacion_IdPostulacionAndRonda(
            String idJurado,
            String idPostulacion,
            Integer ronda
    );

    @Query("""
        SELECT e
        FROM Evaluacion e
        JOIN e.postulacion p
        JOIN p.actividad a
        WHERE a.idActividad = :idActividad
    """)
    List<Evaluacion> obtenerEvaluacionesPorConvocatoria(
        @Param("idActividad") String idActividad
    );

    Evaluacion findByPostulacion_IdPostulacion(String idPostulacion);

    @Query("""
        SELECT
            e.postulacion.idPostulacion AS idPostulacion,
            AVG(e.calificacion) AS promedio,
            COUNT(e.idEvaluacion) AS totalEvaluaciones
        FROM Evaluacion e
        WHERE e.ronda = 1
          AND e.calificacion IS NOT NULL
          AND e.postulacion.idPostulacion IN (
              SELECT p.idPostulacion
              FROM Postulacion p
              WHERE p.actividad.idActividad = :idConvocatoria
          )
        GROUP BY e.postulacion.idPostulacion
    """)
    List<PromedioPostulacionProjection> calcularPromediosRondaUno(
            @Param("idConvocatoria") String idConvocatoria
    );
}
