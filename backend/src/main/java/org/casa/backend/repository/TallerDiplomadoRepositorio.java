package org.casa.backend.repository;

import org.casa.backend.entity.TallerDiplomado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TallerDiplomadoRepositorio extends JpaRepository<TallerDiplomado, String> {
}
