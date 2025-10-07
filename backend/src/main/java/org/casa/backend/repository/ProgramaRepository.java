package org.casa.backend.repository;

import java.util.List;

import org.casa.backend.entity.Programa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgramaRepository extends JpaRepository<Programa, String> {

    List<Programa> findByUsuarioIdUsuario(String idUsuario);

    List<Programa> findByNombrePrograma(String nombrePrograma);

    boolean existsByNombreProgramaAndUsuarioIdUsuario(String nombrePrograma, String idUsuario);

    @Query("SELECT p FROM Programa p JOIN FETCH p.usuario")
    List<Programa> findAllWithDetails();
}