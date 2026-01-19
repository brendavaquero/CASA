package org.casa.backend.clases;

import java.util.Collection;
import java.util.List;

import org.casa.backend.entity.Usuario;
import org.casa.backend.enums.Rol;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserPrincipal implements UserDetails {
    private String idUsuario;
    private String correo;
    private String contrasenia;
    private Rol rol;
    private boolean activo;

    public UserPrincipal(Usuario usuario) {
        this.idUsuario = usuario.getIdUsuario();
        this.correo = usuario.getCorreo();
        this.contrasenia = usuario.getContrasenia();
        this.rol = usuario.getRol();
        this.activo = usuario.isActivo();
    }

    // 🔑 ROLES
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(
            new SimpleGrantedAuthority("ROLE_" + rol.name())
        );
    }

    // 🔐 PASSWORD
    @Override
    public String getPassword() {
        return contrasenia;
    }

    // 👤 USERNAME (correo)
    @Override
    public String getUsername() {
        return correo;
    }

    // 🔓 ESTADOS DE CUENTA
    @Override
    public boolean isEnabled() {
        return activo;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
}
