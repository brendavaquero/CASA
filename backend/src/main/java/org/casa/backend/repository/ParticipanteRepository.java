package org.casa.backend.repository;

import org.casa.backend.entity.Participante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParticipanteRepository extends JpaRepository<Participante, String> {
    Optional<Participante> findByIdUsuario(String idUsuario);
}
