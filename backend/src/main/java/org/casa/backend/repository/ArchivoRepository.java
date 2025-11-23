package org.casa.backend.repository;

import java.util.List;

import org.casa.backend.entity.Archivo;
import org.casa.backend.enums.CategoriaArchivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArchivoRepository extends JpaRepository<Archivo, String> {
    List<Archivo> findByActividad_IdActividad(String idActividad);
    List<Archivo> findByActividad_IdActividadAndCategoria(String idActividad, CategoriaArchivo categoria);

}
