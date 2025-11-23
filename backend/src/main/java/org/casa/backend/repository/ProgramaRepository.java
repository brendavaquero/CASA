package org.casa.backend.repository;


import org.casa.backend.entity.Programa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgramaRepository extends JpaRepository<Programa, String> {
    @Query("SELECT p FROM Programa p JOIN p.responsables r WHERE r.idUsuario = :usuarioId")
    List<Programa> findByResponsableId(@Param("usuarioId") String usuarioId);

}