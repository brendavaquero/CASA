package org.casa.backend.dto;

import java.time.Instant;
import java.time.LocalDateTime;

import org.casa.backend.enums.Rol;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DocenteDto {
    private String idUsuario;
    private String nombre;
    private String apellidos;
    private String correo;
    private String contrasenia;
    private Rol rol;
    private Boolean activo;
    private LocalDateTime ultimoAcceso;
    private Instant fecha_registro;
    private String foto;
    private String semblanza;
}
