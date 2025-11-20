package org.casa.backend.repository;

import org.casa.backend.entity.Archivo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface ArchivoRepository extends JpaRepository<Archivo, String> {
    List<Archivo> findByActividad_IdActividad(String idActividad);
}
