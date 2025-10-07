package org.casa.backend.repository;

import java.util.List;
import java.util.Optional;

import org.casa.backend.entity.Participante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipanteRepository extends JpaRepository<Participante, String> {
    Optional<Participante> findByCurp(String curp);
    
    List<Participante> findByGradoEstudio(String gradoEstudio);
    boolean existsByCurp(String curp);
}
