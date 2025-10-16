package org.casa.backend.repository;

import org.casa.backend.entity.Sesion;
import org.casa.backend.entity.TallerDiplomado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SesionRepository extends JpaRepository<Sesion, String> {
    List<Sesion> findByTallerDiplomado(TallerDiplomado taller);
}
