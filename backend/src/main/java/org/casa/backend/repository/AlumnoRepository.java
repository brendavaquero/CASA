package org.casa.backend.repository;

import java.time.LocalDate;
import java.util.List;
import org.casa.backend.entity.Alumno;
import org.casa.backend.entity.Postulacion;
import org.casa.backend.enums.LenguaInd;
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

    @Query("SELECT al FROM Alumno al " +
            "WHERE al.postulacion.actividad.fechaInicio BETWEEN :inicio AND :fin")
    List<Alumno> findAlumnosByTrimestre(
            @Param("inicio") LocalDate inicio,
            @Param("fin") LocalDate fin);

    @Query("SELECT p.sexo, COUNT(a) FROM Alumno a " +
            "JOIN a.postulacion.participante p " +
            "WHERE a.postulacion.actividad.fechaInicio BETWEEN :inicio AND :fin " +
            "GROUP BY p.sexo")
    List<Object[]> countBySexo(
            @Param("inicio") LocalDate inicio,
            @Param("fin") LocalDate fin);

    @Query("SELECT DISTINCT p.lenguaIndigena FROM Alumno a " +
            "JOIN a.postulacion.participante p " +
            "WHERE a.postulacion.actividad.fechaInicio BETWEEN :inicio AND :fin")
    List<LenguaInd> findLenguasRepresentadas(
            @Param("inicio") LocalDate inicio,
            @Param("fin") LocalDate fin);

    @Query("SELECT p.estado, COUNT(a) FROM Alumno a " +
            "JOIN a.postulacion.participante p " +
            "WHERE a.postulacion.actividad.fechaInicio BETWEEN :inicio AND :fin " +
            "GROUP BY p.estado " +
            "ORDER BY COUNT(a) DESC")
    List<Object[]> topEstados(
            @Param("inicio") LocalDate inicio,
            @Param("fin") LocalDate fin);

    // alumnos admitidos
    @Query("""
        SELECT a
        FROM Alumno a
        WHERE a.postulacion.actividad.fechaInicio BETWEEN :inicio AND :fin
          AND TYPE(a.postulacion.actividad) = TallerDiplomado
    """)
    List<Alumno> findAlumnosTalleresEnPeriodo(
            @Param("inicio") LocalDate inicio,
            @Param("fin") LocalDate fin
    );

    // ======================
    // TOTALES
    // ======================

    @Query("""
        SELECT COUNT(a)
        FROM Alumno a
        JOIN a.postulacion p
        JOIN p.actividad act
        WHERE act.fechaInicio BETWEEN :inicio AND :fin
        AND TYPE(act) = TallerDiplomado
    """)
    long contarAlumnosEnTalleres(
            @Param("inicio") LocalDate inicio,
            @Param("fin") LocalDate fin
    );

    @Query("""
        SELECT COUNT(a)
        FROM Alumno a
        JOIN a.postulacion p
        JOIN p.actividad act
        WHERE act.fechaInicio BETWEEN :inicio AND :fin
        AND TYPE(act) = TallerDiplomado
        AND act.infantil = true
    """)
    long contarAlumnosEnTalleresInfantiles(
            @Param("inicio") LocalDate inicio,
            @Param("fin") LocalDate fin
    );

    // ======================
    // SEXO
    // ======================

    @Query("""
        SELECT part.sexo, COUNT(a)
        FROM Alumno a
        JOIN a.postulacion p
        JOIN p.participante part
        JOIN p.actividad act
        WHERE act.fechaInicio BETWEEN :inicio AND :fin
        AND TYPE(act) = TallerDiplomado
        GROUP BY part.sexo
    """)
    List<Object[]> contarAlumnosPorSexo(
            @Param("inicio") LocalDate inicio,
            @Param("fin") LocalDate fin
    );

    // ======================
    // GEOGRAFÍA
    // ======================

    @Query("""
        SELECT part.pais, COUNT(a)
        FROM Alumno a
        JOIN a.postulacion p
        JOIN p.participante part
        JOIN p.actividad act
        WHERE act.fechaInicio BETWEEN :inicio AND :fin
        AND TYPE(act) = TallerDiplomado
        GROUP BY part.pais
    """)
    List<Object[]> contarAlumnosPorPais(
            @Param("inicio") LocalDate inicio,
            @Param("fin") LocalDate fin
    );

    @Query("""
        SELECT part.estado, COUNT(a)
        FROM Alumno a
        JOIN a.postulacion p
        JOIN p.participante part
        JOIN p.actividad act
        WHERE act.fechaInicio BETWEEN :inicio AND :fin
        AND TYPE(act) = TallerDiplomado
        GROUP BY part.estado
    """)
    List<Object[]> contarAlumnosPorEstado(
            @Param("inicio") LocalDate inicio,
            @Param("fin") LocalDate fin
    );

    // ======================
    // EDAD (fecha nac)
    // ======================

    @Query("""
        SELECT part.fechaNacimiento
        FROM Alumno a
        JOIN a.postulacion p
        JOIN p.participante part
        JOIN p.actividad act
        WHERE act.fechaInicio BETWEEN :inicio AND :fin
        AND TYPE(act) = TallerDiplomado
    """)
    List<LocalDate> obtenerFechasNacimientoAlumnos(
            @Param("inicio") LocalDate inicio,
            @Param("fin") LocalDate fin
    );

    @Query("""
    SELECT part.sexo, COUNT(a)
    FROM Alumno a
    JOIN a.postulacion p
    JOIN p.participante part
    JOIN p.actividad act
    WHERE act.fechaInicio BETWEEN :inicio AND :fin
    AND TYPE(act) = TallerDiplomado
    GROUP BY part.sexo
""")
    List<Object[]> contarPorSexo(
            @Param("inicio") LocalDate inicio,
            @Param("fin") LocalDate fin
    );

    @Query("""
        SELECT a
        FROM Alumno a
        WHERE a.postulacion.actividad.fechaInicio
        BETWEEN :inicio AND :fin
    """)
    List<Alumno> alumnosEnPeriodo(LocalDate inicio, LocalDate fin);


}