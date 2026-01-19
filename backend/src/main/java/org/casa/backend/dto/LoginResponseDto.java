package org.casa.backend.dto;

import org.casa.backend.enums.Rol;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class LoginResponseDto {
    private String token;
    private Rol rol;
    private String idUsuario;
    private String nombre;
    private String apellidos;
    private String correo;
}
