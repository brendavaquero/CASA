package org.casa.backend.repository;

import java.time.LocalDate;
import java.util.List;

import org.casa.backend.entity.Actividad;
import org.casa.backend.entity.TallerDiplomado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TallerDiplomadoRepository extends JpaRepository<TallerDiplomado, String> {
    List<TallerDiplomado> findByDocenteIdUsuario(String idUsuario);
    @Query("SELECT a FROM Actividad a " +
            "WHERE a.tipo = 'TALLER_DIPLOMADO' " +
            "AND a.fechaInicio BETWEEN :inicio AND :fin")
    List<Actividad> findTalleresByTrimestre(
            @Param("inicio") LocalDate inicio,
            @Param("fin") LocalDate fin);

}
