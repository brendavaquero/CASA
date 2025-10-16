package org.casa.backend.dto;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.casa.backend.enums.Estado;
import org.casa.backend.enums.GradoEstudio;
import org.casa.backend.enums.LenguaInd;
import org.casa.backend.enums.Municipio;
import org.casa.backend.enums.Pais;
import org.casa.backend.enums.Rol;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParticipanteDto {
    private String idUsuario;
    private String nombre;
    private String apellidos;
    private String correo;
    private String contrasenia;
    private Rol rol;
    private Boolean activo;
    private LocalDateTime ultimoAcceso;
    private Instant fecha_registro;
    private String nombreParticipante;
    private Character sexo;
    private LocalDate fechaNacimiento;
    private String curp;
    private String numeroTelefono;
    private Integer codigoPostal;
    private Pais pais;
    private Estado estado;
    private Municipio municipio;
    private GradoEstudio gradoEstudio;
    private String ocupacion;
    private LenguaInd lenguaIndigena;
    private String seudonimo;
}
