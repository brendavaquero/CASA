package org.casa.backend.repository;

import org.casa.backend.entity.Evaluacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvaluacionRepository extends JpaRepository<Evaluacion, String>{
    boolean existsByJurado_IdJuradoAndPostulacion_IdPostulacionAndRonda(
            String idJurado,
            String idPostulacion,
            Integer ronda
    );
}
