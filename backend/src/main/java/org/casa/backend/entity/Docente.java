package org.casa.backend.entity;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.casa.backend.enums.Rol;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="docente")
@PrimaryKeyJoinColumn(name = "id_usuario")
public class Docente extends Usuario{
    @Column(name = "foto", length = 500)
    private String foto;

    @Column(name = "semblanza", columnDefinition = "TEXT")
    private String semblanza;

    @OneToMany(mappedBy = "docente", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<TallerDiplomado> talleres = new ArrayList<>();

    public Docente() {
        super();
    }

    public Docente(String idUsuario, String nombre, String apellidos, String correo, String contrasenia, Rol rol, boolean activo, Instant fecha_registro, LocalDateTime ultimo_acceso) {
        super(idUsuario, nombre, apellidos, correo, contrasenia, rol, activo, fecha_registro, ultimo_acceso);
    }

    public Docente(
            String idUsuario,
            String nombre,
            String apellidos,
            String correo,
            String contrasenia,
            Rol rol,
            boolean activo,
            Instant fecha_registro,
            LocalDateTime ultimo_acceso,
            String foto,
            String semblanza
    ){
        super(
                idUsuario,
                nombre,
                apellidos,
                correo,
                contrasenia,
                rol,
                activo,
                fecha_registro,
                ultimo_acceso
        );
        this.foto=foto;
        this.semblanza=semblanza;
    }
}
