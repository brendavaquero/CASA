package org.casa.backend.repository;

import org.casa.backend.entity.Actividad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface ActividadRepository extends JpaRepository<Actividad, String> {
    long countByFechaInicioBetween(LocalDate inicio, LocalDate fin);
    long countByFechaInicioBetweenAndInfantilTrue(LocalDate inicio, LocalDate fin);

    @Query("""
        SELECT COUNT(a)
        FROM Actividad a
        WHERE a.fechaInicio BETWEEN :inicio AND :fin
          AND TYPE(a) = :tipo
    """)
    long countByFechaInicioBetweenAndTipo(
            @Param("inicio") LocalDate inicio,
            @Param("fin") LocalDate fin,
            @Param("tipo") Class<? extends Actividad> tipo
    );

    // ======================
    // TALLERES
    // ======================

    @Query("""
        SELECT COUNT(t)
        FROM TallerDiplomado t
        WHERE t.fechaInicio BETWEEN :inicio AND :fin
    """)
    long contarTalleres(
            @Param("inicio") LocalDate inicio,
            @Param("fin") LocalDate fin
    );

    @Query("""
        SELECT COUNT(t)
        FROM TallerDiplomado t
        WHERE t.fechaInicio BETWEEN :inicio AND :fin
        AND t.infantil = true
    """)
    long contarTalleresInfantiles(
            @Param("inicio") LocalDate inicio,
            @Param("fin") LocalDate fin
    );

    // ======================
    // CONVOCATORIAS
    // ======================

    @Query("""
        SELECT COUNT(c)
        FROM ConvocatoriaResidencia c
        WHERE c.fechaInicio BETWEEN :inicio AND :fin
    """)
    long contarConvocatorias(
            @Param("inicio") LocalDate inicio,
            @Param("fin") LocalDate fin
    );

    @Query("""
        SELECT COUNT(c)
        FROM ConvocatoriaResidencia c
        WHERE c.fechaInicio BETWEEN :inicio AND :fin
        AND c.infantil = true
    """)
    long contarConvocatoriasInfantiles(
            @Param("inicio") LocalDate inicio,
            @Param("fin") LocalDate fin
    );
}
