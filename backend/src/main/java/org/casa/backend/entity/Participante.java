package org.casa.backend.entity;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.casa.backend.enums.Estado;
import org.casa.backend.enums.GradoEstudio;
import org.casa.backend.enums.LenguaInd;
import org.casa.backend.enums.Municipio;
import org.casa.backend.enums.Pais;
import org.casa.backend.enums.Rol;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="participante")
@PrimaryKeyJoinColumn(name = "id_usuario")
public class Participante extends Usuario{
    @Column(name = "nombreparticipante", length = 50)
    private String nombreParticipante;

    @Column(name = "sexo", length = 1)
    private Character sexo;

    @Column(name = "fecha_nac")
    private LocalDate fechaNacimiento;

    @Column(name = "curp", length = 20, unique = true)
    private String curp;

    @Column(name = "num_tel",length = 15)
    private String numeroTelefono;

    @Column(name = "cp")
    private Integer codigoPostal;

    @Enumerated(EnumType.STRING)
    @Column(name = "pais")
    private Pais pais;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", length = 100, nullable = true)
    private Estado estado;

    @Enumerated(EnumType.STRING)
    @Column(name = "municipio", length = 100, nullable = true)
    private Municipio municipio;

    @Enumerated(EnumType.STRING)
    @Column(name = "grado_estudio", length = 50)
    private GradoEstudio gradoEstudio;

    @Column(name = "ocupacion", length = 100)
    private String ocupacion;

    @Enumerated(EnumType.STRING)
    @Column(name = "lengua_ind", length = 50)
    private LenguaInd lenguaIndigena;

    @Column(name = "seudonimo", length = 100)
    private String seudonimo;

    public Participante() {
        super();
    }

    public Participante(String idUsuario, String nombre, String apellidos, String correo, String contrasenia, Rol rol, boolean activo, Instant fecha_registro, LocalDateTime ultimo_acceso) {
        super(idUsuario, nombre, apellidos, correo, contrasenia, rol, activo, fecha_registro, ultimo_acceso);
    }
}
