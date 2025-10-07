package org.casa.backend.repository;
/*
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;*/

import org.casa.backend.entity.Asistencia;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AsistenciaRepository extends JpaRepository<Asistencia, String> {
/*
    List<Asistencia> findByAlumnoIdAlumno(String idAlumno);
    List<Asistencia> findByFecha(LocalDate fecha);
    List<Asistencia> findByPresente(Boolean presente);
    Optional<Asistencia> findByAlumnoIdAlumnoAndFecha(String idAlumno, LocalDate fecha);
    List<Asistencia> findByFechaBetween(LocalDate fechaInicio, LocalDate fechaFin);
    List<Asistencia> findByAlumnoIdAlumnoAndFechaBetween(String idAlumno, LocalDate fechaInicio, LocalDate fechaFin);
    boolean existsByAlumnoIdAlumnoAndFecha(String idAlumno, LocalDate fecha);
    long countByPresente(Boolean presente);
    long countByAlumnoIdAlumnoAndPresente(String idAlumno, Boolean presente);
    @Query("SELECT a FROM Asistencia a JOIN FETCH a.alumno al JOIN FETCH al.postulacion p JOIN FETCH p.participante JOIN FETCH p.actividad WHERE a.idAsistencia = :id")
    Optional<Asistencia> findByIdWithDetails(@Param("id") String id);
    @Query("SELECT a FROM Asistencia a JOIN a.alumno al JOIN al.postulacion p JOIN p.actividad act WHERE act.idActividad = :idActividad")
    List<Asistencia> findByActividadId(@Param("idActividad") String idActividad);
    @Query("SELECT a FROM Asistencia a JOIN a.alumno al JOIN al.postulacion p JOIN p.actividad act WHERE act.idActividad = :idActividad AND a.fecha = :fecha")
    List<Asistencia> findByActividadIdAndFecha(@Param("idActividad") String idActividad, @Param("fecha") LocalDate fecha);
*/
}