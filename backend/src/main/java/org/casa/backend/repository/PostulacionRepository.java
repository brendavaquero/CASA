package org.casa.backend.repository;

//import java.util.List;
//import java.util.Optional;

import org.casa.backend.entity.Postulacion;
//import org.casa.backend.enums.EstadoPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostulacionRepository extends JpaRepository<Postulacion, String>{
    /*
    List<Postulacion> findByParticipanteIdUsuario(String idUsuario);
    List<Postulacion> findByActividadIdActividad(String idActividad);
    List<Postulacion> findByEstado(EstadoPost estado);
    Optional<Postulacion> findByParticipanteIdUsuarioAndActividadIdActividad(String idUsuario, String idActividad);*/
}
