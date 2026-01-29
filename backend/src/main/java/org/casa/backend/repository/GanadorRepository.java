package org.casa.backend.repository;

import java.util.List;

import org.casa.backend.dto.GanadorConvocatoriaDto;
import org.casa.backend.dto.GanadorDto;
import org.casa.backend.entity.Ganador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GanadorRepository extends JpaRepository<Ganador, String>{
//    boolean existsByResultado_idPostulacion_Convocatoria_IdConvocatoria(
//            String idConvocatoria
//    );
    Optional<Ganador> findFirstByResultado_IdConvocatoria(String idConvocatoria);
    List<Ganador> findByResultado_IdConvocatoria(String idConvocatoria);
    boolean existsByResultado_IdConvocatoria(String idConvocatoria);

    @Query("SELECT p.nombre AS nombre, p.apellidos AS apellidos " +
            "FROM Ganador g " +
            "JOIN g.resultado r " +              // relación ManyToOne de Ganador → ResultadoRondaUno
            "JOIN Postulacion po ON po.idPostulacion = r.idPostulacion " + // join por ID porque ResultadoRondaUno no tiene relación directa
            "JOIN po.participante p " +          // relación ManyToOne de Postulacion → Participante
            "WHERE g.idGanador = :idGanador")
    Optional<GanadorConvocatoriaDto> findNombreYApellidosByIdGanador(@Param("idGanador") String idGanador);

}
