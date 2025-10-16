package org.casa.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.casa.backend.enums.Rol;

import java.time.Instant;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDto {
    private String idUsuario;
    private String nombre;
    private String apellidos;
    private String correo;
    private String contrasenia;
    private Rol rol;
    private boolean activo;
    private Instant fecha_registro;
    private LocalDateTime ultimo_acceso;
}
