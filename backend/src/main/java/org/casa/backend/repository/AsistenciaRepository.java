package org.casa.backend.repository;

import java.util.List;

import org.casa.backend.entity.Asistencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AsistenciaRepository extends JpaRepository<Asistencia, String> {
    @Query(value = """
        SELECT 
            u.id_usuario,
            u.nombre,
            u.apellidos,
            al.id_alumno,
            COUNT(CASE WHEN asi.presente = true THEN 1 END) AS asistencias_presentes,
            (COUNT(CASE WHEN asi.presente = true THEN 1 END) * 1.0 / ts.total_sesiones) AS porcentaje_asistencia
        FROM alumno al
        INNER JOIN postulacion p ON al.id_postulacion = p.id_postulacion
        INNER JOIN participante part ON p.id_usuario = part.id_usuario
        INNER JOIN usuario u ON part.id_usuario = u.id_usuario
        INNER JOIN actividad act ON p.id_actividad = act.id_actividad
        INNER JOIN taller_diplomado t ON act.id_actividad = t.id_actividad
        LEFT JOIN asistencia asi ON asi.id_alumno = al.id_alumno
        CROSS JOIN (
            SELECT COUNT(*) AS total_sesiones
            FROM sesion 
            WHERE id_taller_diplomado = :idActividad
        ) ts
        WHERE t.id_actividad = :idActividad
        GROUP BY u.id_usuario, u.nombre, u.apellidos, al.id_alumno, ts.total_sesiones
        HAVING (COUNT(CASE WHEN asi.presente = true THEN 1 END) * 1.0 / ts.total_sesiones) >= 0.80
    """, nativeQuery = true)
    List<Object[]> obtenerAsistenciasPorTaller(@Param("idActividad") String idActividad);
    List<Asistencia> findByAlumno_IdAlumno(String idAlumno);
}