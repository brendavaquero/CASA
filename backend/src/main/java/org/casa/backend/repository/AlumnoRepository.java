package org.casa.backend.repository;

import java.util.List;
import org.casa.backend.entity.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, String> {
    List<Alumno> findByPostulacionIdPostulacion(String idPostulacion);
}