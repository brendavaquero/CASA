package org.casa.backend.repository;


import java.util.List;

import org.casa.backend.entity.Postulacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostulacionRepository extends JpaRepository<Postulacion, String>{
    //Consulta para obtener todos los alumnos de cierta actividad
    @Query(value = """ 
        SELECT 
            u.nombre AS nombre,
            u.apellidos AS apellidos,
            a.id_alumno AS idAlumno,
            po.id_postulacion AS idPostulacion,
            ac.titulo AS tituloActividad
        FROM usuario u
        INNER JOIN participante pa ON pa.id_usuario = u.id_usuario
        INNER JOIN postulacion po ON po.id_usuario = u.id_usuario
        INNER JOIN alumno a ON a.id_postulacion = po.id_postulacion
        INNER JOIN actividad ac ON ac.id_actividad = po.id_actividad
        WHERE po.id_actividad = :idActividad
        ORDER BY u.id_usuario ASC """,
        nativeQuery = true)
    
    List<Object[]> alumnosByActividad(@Param("idActividad") String idActividad);
}
