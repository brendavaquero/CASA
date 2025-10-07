package org.casa.backend.repository;

import org.casa.backend.entity.Actividad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActividadRepository extends JpaRepository<Actividad, String> {
    
}
