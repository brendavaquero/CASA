package org.casa.backend.repository;

import java.util.List;

import org.casa.backend.entity.Evaluacion;
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
}
