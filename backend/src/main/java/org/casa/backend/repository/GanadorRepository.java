package org.casa.backend.repository;

import org.casa.backend.entity.Ganador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GanadorRepository extends JpaRepository<Ganador, String>{
//    boolean existsByResultado_idPostulacion_Convocatoria_IdConvocatoria(
//            String idConvocatoria
//    );
}
