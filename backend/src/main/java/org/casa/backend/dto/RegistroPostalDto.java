package org.casa.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.casa.backend.enums.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistroPostalDto {

    // Usuario

    @NotBlank
    private String nombre;

    @NotBlank
    private String apellidos;

    @Email
    @NotBlank
    private String correo;

    // participante

    private Character sexo;

    private LocalDate fechaNacimiento;

    @NotBlank
    private String curp;

    private String numeroTelefono;

    private Integer codigoPostal;

    private Pais pais;

    private Estado estado;

    private String municipio;

    private GradoEstudio gradoEstudio;

    private String ocupacion;

    private LenguaInd lenguaIndigena;

    private String seudonimo;
}

