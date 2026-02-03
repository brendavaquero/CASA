package org.casa.backend.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.casa.backend.entity.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectorRepository extends JpaRepository<Director, Long> {
    Director findByActivoTrue();
    Optional<Director> findByActivoTrueAndFechaInicioLessThanEqualAndFechaFinGreaterThanEqual(
            LocalDate hoy1,
            LocalDate hoy2
    );
}
