package org.casa.backend.entity;


import java.time.LocalDateTime;
import org.casa.backend.enums.Rol;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
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

    @Column(name = "activo")
    private boolean activo = true;

    @Column(name = "fecha_registro",columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime  fecha_registro;

    @Column(name = "ultimo_acceso", columnDefinition = "timestamp without time zone")
    private LocalDateTime  ultimo_acceso = LocalDateTime.now();;

    @Column(name = "contrasenia", nullable = false)
    private String contrasenia;
    public Usuario(String nombre, String apellidos, String correo, String contrasenia, Rol rol) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.correo = correo;
        this.contrasenia = contrasenia;
        this.rol = rol;
        this.activo = true;
        this.ultimo_acceso = LocalDateTime.now();
    }
    /* 
    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario='" + idUsuario + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", correo='" + correo + '\'' +
                ", contrasenia='" + contrasenia + '\'' +
                ", rol=" + rol +
                ", activo=" + activo +
                ", fechaRegistro=" + fecha_registro +
                ", ultimoAcceso=" + ultimo_acceso +
                '}';
    }*/
}
