package org.casa.backend.repository;

import java.util.List;

import org.casa.backend.entity.Asistencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AsistenciaRepository extends JpaRepository<Asistencia, String> {
    List<Asistencia> findByAlumno_IdAlumno(String idAlumno);
}