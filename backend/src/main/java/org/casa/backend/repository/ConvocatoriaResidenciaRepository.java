package org.casa.backend.repository;

import org.casa.backend.entity.ConvocatoriaResidencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface ConvocatoriaResidenciaRepository extends JpaRepository<ConvocatoriaResidencia, String> {
    @Query("""
        SELECT COUNT(c)
        FROM ConvocatoriaResidencia c
        WHERE c.fechaInicio BETWEEN :inicio AND :fin
    """)
    Long contarConvocatoriasEnPeriodo(LocalDate inicio, LocalDate fin);
    boolean existsByTituloIgnoreCaseAndFechaInicioAndFechaCierreAndFechaResultados(
        String titulo,
        LocalDate fechaInicio,
        LocalDate fechaCierre,
        LocalDate fechaResultados
    );
}
