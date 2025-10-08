package org.casa.backend.repository;

import org.casa.backend.entity.Archivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArchivoRepositorio extends JpaRepository<Archivo, String> {
}
