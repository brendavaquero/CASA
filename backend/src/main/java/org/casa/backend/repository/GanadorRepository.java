package org.casa.backend.repository;

import java.util.List;

import org.casa.backend.entity.Ganador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GanadorRepository extends JpaRepository<Ganador, String>{
//    boolean existsByResultado_idPostulacion_Convocatoria_IdConvocatoria(
//            String idConvocatoria
//    );
    //Optional<Ganador> findByResultado_IdConvocatoria(String idConvocatoria);
    List<Ganador> findByResultado_IdConvocatoria(String idConvocatoria);
}
