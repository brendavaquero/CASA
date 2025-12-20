package org.casa.backend.repository;

import org.casa.backend.entity.Obra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObraRepository extends JpaRepository<Obra, String> {
  
}
