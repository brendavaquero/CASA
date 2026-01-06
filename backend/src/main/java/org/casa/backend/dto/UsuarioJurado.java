package org.casa.backend.dto;

import org.casa.backend.enums.Rol;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioJurado {
    private String idJurado;
    private String idUsuario;
    private String idConvocatoria;
    private String nombre;
    private String apellidos;
    private String correo;
    private String contrasenia;
    private Rol rol;
}
