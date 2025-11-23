package org.casa.backend.repository;

import java.util.List;
import org.casa.backend.entity.Alumno;
import org.casa.backend.entity.Postulacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, String> {
    List<Alumno> findByPostulacionIdPostulacion(String idPostulacion);
    @Query(value = """
        SELECT 
            u.id_usuario,
            u.nombre,
            u.apellidos,
            a.id_alumno,
            ac.id_actividad,
            ac.titulo
        FROM alumno a
        INNER JOIN postulacion po ON a.id_postulacion = po.id_postulacion
        INNER JOIN participante pa ON pa.id_usuario = po.id_usuario
        INNER JOIN usuario u ON u.id_usuario = pa.id_usuario
        INNER JOIN actividad ac ON ac.id_actividad = po.id_actividad
        INNER JOIN taller_diplomado t ON t.id_actividad = ac.id_actividad
        WHERE u.id_usuario = :idUsuario
        """, nativeQuery = true)
    List<Object[]> obtenerTalleresDeAlumno(@Param("idUsuario") String idUsuario);
    boolean existsByPostulacion(Postulacion postulacion);
    boolean existsByPostulacion_IdPostulacion(String idPostulacion);
    List<Alumno> findByPostulacion_Actividad_IdActividad(String idActividad);
}