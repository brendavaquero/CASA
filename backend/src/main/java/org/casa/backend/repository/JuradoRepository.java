package org.casa.backend.repository;

import java.util.List;
import java.util.Optional;

import org.casa.backend.dto.JuradoConvocatoriaDto;
import org.casa.backend.dto.JuradoDto;
import org.casa.backend.entity.Jurado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface JuradoRepository extends JpaRepository<Jurado, String>{
    List<Jurado> findByConvocatoriaIdActividad(String convocatoriaId);
    @Query("""
    SELECT new org.casa.backend.dto.JuradoConvocatoriaDto(
        j.idJurado,
        u.idUsuario,
        u.nombre,
        u.apellidos,
        u.correo,
        c.titulo,
        c.descripcion,
        c.fechaInicio,
        c.fechaCierre,
        c.fechaResultados,
        c.requisitos,
        c.tipo,
        c.imagen,
        c.idActividad,
        c.bases,
        c.premio,
        c.convocantes,
        c.fechaInicioR1,
        c.fechaLimiteR1,
        c.estado
        )
        FROM Jurado j
        JOIN j.convocatoria c
        JOIN j.usuario u
        WHERE u.idUsuario = :idUsuario
    """)
    List<JuradoConvocatoriaDto> findConvocatoriasByUsuario(@Param("idUsuario") String idUsuario);

    Optional<Jurado> findByIdJurado(String idJurado);
    long countByConvocatoria_IdActividad(String idConvocatoria);
    //long countByIdActividad(String idConvocatoria);
    //JuradoDto save(JuradoDto juradoDto);
}
