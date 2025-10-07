package org.casa.backend.repository;

import java.util.Optional;

import org.casa.backend.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {

    Optional<Usuario> findByCorreo(String correo);
    boolean existsByCorreo(String correo);
    
    boolean existsByIdUsuario(String idUsuario);
}
