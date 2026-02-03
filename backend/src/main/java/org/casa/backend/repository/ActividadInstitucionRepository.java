package org.casa.backend.repository;


import org.casa.backend.dto.ActividadInstitucionDTO;
import org.casa.backend.entity.ActividadInstitucion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ActividadInstitucionRepository extends JpaRepository<ActividadInstitucion, Long> {

    List<ActividadInstitucion> findByActividad_IdActividad(String idActividad);
}