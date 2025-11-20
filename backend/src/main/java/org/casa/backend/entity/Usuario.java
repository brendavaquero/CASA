package org.casa.backend.entity;


import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import org.casa.backend.enums.Rol;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="usuario")
@Inheritance(strategy = InheritanceType.JOINED)

public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario", length = 20)
    private String idUsuario;

    @Column(name = "num", insertable = false, updatable = false)
    private Integer num;

    @Column(name = "nombre", length = 25, nullable = false)
    private String nombre;

    
    @Column(name = "apellidos", length = 50, nullable = false)
    private String apellidos;

   
    @Column(name = "correo",length = 50, nullable = false)
    private String correo;

    @Enumerated(EnumType.STRING)
    @Column(name = "rol", length = 20, nullable = false)
    private Rol rol;

    @Column(name = "activo", insertable = false)
    private boolean activo = true;

    @Column(name = "fecha_registro", insertable = false, updatable = false)
    private Instant  fecha_registro;

    @Column(name = "ultimo_acceso", insertable = false)
    private LocalDateTime  ultimo_acceso = LocalDateTime.now();;

    @Column(name = "contrasenia", nullable = false)
    private String contrasenia;

    public Usuario(String idUsuario, String nombre, String apellidos, String correo, String contrasenia, Rol rol, boolean activo, Instant fecha_registro, LocalDateTime ultimo_acceso) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.correo = correo;
        this.contrasenia = contrasenia;
        this.rol = rol;
        this.activo = activo;
        this.fecha_registro = fecha_registro;
        this.ultimo_acceso = ultimo_acceso;
    }

    @ManyToMany(mappedBy = "responsables")
    private List<Programa> programas = new ArrayList<>();

}
