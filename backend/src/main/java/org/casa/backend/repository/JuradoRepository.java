package org.casa.backend.repository;

import org.casa.backend.entity.Jurado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JuradoRepository extends JpaRepository<Jurado, String>{

}