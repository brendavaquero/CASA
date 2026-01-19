package org.casa.backend.config;

import org.apache.tomcat.util.http.parser.HttpParser;
import org.casa.backend.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;

@Configuration
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http
            .cors(Customizer.withDefaults())
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/auth/**").permitAll()
                .requestMatchers("/api/usuarios/me/**").authenticated()//Actualizar ultimo acceso
                
                //Público
                .requestMatchers("/uploads/**").permitAll()
                .requestMatchers("/auth/register/participante").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/convocatoriasyresidencias/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/talleresydiplomados/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/sesiones/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/archivos/**").permitAll()
                //PARA PROBAR: DEBO CAMBIARLO DESPUES*
                .requestMatchers("/api/email/**").hasRole("ADMINISTRADOR")

                //Jurado
                .requestMatchers(HttpMethod.GET, "/api/jurado/**").hasAnyRole("ADMINISTRADOR", "JURADO")
                .requestMatchers(HttpMethod.GET, "/api/evaluacion/**").hasAnyRole("ADMINISTRADOR","JURADO")
                .requestMatchers(HttpMethod.POST,"/api/evaluacion/**").hasAnyRole("ADMINISTRADOR","JURADO")


                //ADMIN
                .requestMatchers(HttpMethod.PUT, "/api/talleresydiplomados/**").hasRole("ADMINISTRADOR")
                .requestMatchers(HttpMethod.DELETE, "/api/talleresydiplomados/**").hasRole("ADMINISTRADOR")
                .requestMatchers(HttpMethod.POST, "/api/convocatoriasyresidencias/**").hasRole("ADMINISTRADOR")
                .requestMatchers(HttpMethod.PUT, "/api/convocatoriasyresidencias/**").hasRole("ADMINISTRADOR")
                .requestMatchers(HttpMethod.DELETE, "/api/convocatoriasyresidencias/**").hasRole("ADMINISTRADOR")
                .requestMatchers(HttpMethod.DELETE, "/api/postulaciones/**").hasRole("ADMINISTRADOR")
                .requestMatchers(HttpMethod.POST, "/api/sesiones/**").hasRole("ADMINISTRADOR")
                .requestMatchers(HttpMethod.PUT, "/api/sesiones/**").hasRole("ADMINISTRADOR")
                .requestMatchers(HttpMethod.DELETE, "/api/sesiones/**").hasRole("ADMINISTRADOR")

                //Permisos para admin y docente
                .requestMatchers("/api/asistencias/**").hasAnyRole("ADMINISTRADOR","DOCENTE")
                .requestMatchers(HttpMethod.POST, "/api/talleresydiplomados/**").hasAnyRole("ADMINISTRADOR","DOCENTE")
                
                //Permisos participante y admin
                .requestMatchers(HttpMethod.POST, "/api/postulaciones/**").hasAnyRole("PARTICIPANTE","DOCENTE","ADMINISTRADOR","AUXILIAR")
                .requestMatchers(HttpMethod.GET,"/api/alumnos/**").hasAnyRole("PARTICIPANTE","DOCENTE")
                .requestMatchers(HttpMethod.GET, "/api/postulaciones/**").hasAnyRole("ADMINISTRADOR", "JURADO", "DOCENTE")
                .requestMatchers(HttpMethod.PUT, "/api/postulaciones/**").hasAnyRole("ADMINISTRADOR", "JURADO","DOCENTE")

                //Auxiliar
                .requestMatchers(HttpMethod.GET, "/api/programas/**").hasRole("INVITADO")

                //Mas de 2 Roles
                .requestMatchers("/api/archivos/**").hasAnyRole("ADMINISTRADOR","DOCENTE","INVITADO","PARTICIPANTE","AUXILIAR")
                .requestMatchers("/api/programas/**").hasAnyRole("ADMINISTRADOR","AUXILIAR")
                .requestMatchers("/api/reportes/**").hasAnyRole("ADMINISTRADOR","AUXILIAR")
                
                //Permisos para admin y jurado
                .requestMatchers("/api/evaluacion/**").hasAnyRole("ADMINISTRADOR","JURADO")

                //ADMIN
                .requestMatchers("/api/usuarios/**").hasRole("ADMINISTRADOR")
                .requestMatchers("/api/participantes/**").hasRole("ADMINISTRADOR")
                .requestMatchers("/api/docentes/**").hasAnyRole("ADMINISTRADOR","DOCENTE")
                .requestMatchers("/api/ganador/**").hasRole("ADMINISTRADOR")
                .requestMatchers("/api/jurado/**").hasRole("ADMINISTRADOR")
                .requestMatchers("/api/alumnos/**").hasRole("ADMINISTRADOR")

                //.requestMatchers(HttpMethod.POST, "/api/usuarios").permitAll()
                .requestMatchers("/administrador/**").hasRole("ADMINISTRADOR")
                //.requestMatchers(HttpMethod.GET, "/api/usuarios/**").hasRole("ADMINISTRADOR")
                .requestMatchers("/docente/**").hasRole("DOCENTE")
                .requestMatchers("/auxiliar/**").hasRole("AUXILIAR")
                .requestMatchers("/invitado/**").hasRole("INVITADO")
                .requestMatchers("/participante/**").hasRole("PARTICIPANTE")
                .requestMatchers("/jurado/**").hasRole("JURADO")
                .anyRequest().authenticated()
            )
            .addFilterBefore(jwtFilter,
                    org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // 🔐 PASSWORD ENCODER (AQUÍ VA)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Necesario para el login
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}

