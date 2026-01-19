package org.casa.backend.repository;

import org.casa.backend.dto.FinalistaDto;
import org.casa.backend.dto.ResultadoRondaUnoDto;
import org.casa.backend.entity.ResultadoRondaUno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultadoRondaUnoRepository extends JpaRepository<ResultadoRondaUno, String> {

    boolean existsByIdConvocatoria(String idConvocatoria);

    List<ResultadoRondaUno> findByIdConvocatoriaOrderByPosicionAsc(
            String idConvocatoria
    );

    List<ResultadoRondaUno> findByIdConvocatoriaAndFinalistaTrueOrderByPosicionAsc(
            String idConvocatoria
    );

    ResultadoRondaUnoDto findByIdConvocatoriaAndIdPostulacion( String idConvocatoria, String idPostulacion);

    @Query("""
SELECT new org.casa.backend.dto.FinalistaDto(
    r.idConvocatoria,
    po.actividad.infantil,
    r.idResultado,
    r.idPostulacion,

    po.participante.nombre,
    po.participante.apellidos,
    po.postulante,
    po.nombreObra,
    ar.ruta,
    r.promedio,
    r.posicion
)
FROM ResultadoRondaUno r
JOIN Postulacion po ON po.idPostulacion = r.idPostulacion
JOIN Participante p ON p.idUsuario = po.participante.idUsuario
JOIN Archivo ar ON ar.postulacion.idPostulacion = po.idPostulacion
WHERE r.idConvocatoria = :idConvocatoria
AND r.finalista = true
ORDER BY r.posicion
""")
    List<FinalistaDto> obtenerFinalistasDTO(String idConvocatoria);

}