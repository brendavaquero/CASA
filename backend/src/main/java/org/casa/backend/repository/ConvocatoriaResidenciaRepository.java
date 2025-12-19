package org.casa.backend.repository;

import org.casa.backend.entity.ConvocatoriaResidencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConvocatoriaResidenciaRepository extends JpaRepository<ConvocatoriaResidencia, String> {

}
