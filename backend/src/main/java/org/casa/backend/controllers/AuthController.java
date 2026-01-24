package org.casa.backend.controllers;

import org.casa.backend.dto.LoginRequestDto;
import org.casa.backend.dto.LoginResponseDto;
import org.casa.backend.dto.ParticipanteDto;
import org.casa.backend.entity.Usuario;
import org.casa.backend.repository.UsuarioRepository;
import org.casa.backend.security.JwtService;
import org.casa.backend.service.ParticipanteService;
import org.casa.backend.service.UsuarioDetailsService;
import org.casa.backend.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UsuarioDetailsService userDetailsService;
    private final UsuarioRepository usuarioRepository;
    private final UsuarioService usuarioService;
    private final JwtService jwtService;
    private final ParticipanteService participanteService;

    public AuthController(
            AuthenticationManager authenticationManager,
            UsuarioDetailsService userDetailsService,
            UsuarioRepository usuarioRepository,
            JwtService jwtService,
            ParticipanteService participanteService,
            UsuarioService usuarioService
    ) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.usuarioRepository = usuarioRepository;
        this.jwtService = jwtService;
        this.participanteService = participanteService;
        this.usuarioService = usuarioService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(
            @RequestBody LoginRequestDto request
    ) {

        //Autenticación (Spring Security)
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getCorreo(),
                        request.getContrasenia()
                )
        );

        //Cargar UserDetails
        UserDetails userDetails =
                userDetailsService.loadUserByUsername(request.getCorreo());

        usuarioService.actualizarUltimoAcceso(request.getCorreo());

        //Generar JWT
        String token = jwtService.generateToken(userDetails);

        //Info extra para el frontend
        Usuario usuario = usuarioRepository
                .findByCorreo(request.getCorreo())
                .orElseThrow();

        return ResponseEntity.ok(
                new LoginResponseDto(
                        token,
                        usuario.getRol(),
                        usuario.getIdUsuario(),
                        usuario.getNombre(),
                        usuario.getApellidos(),
                        usuario.getCorreo()
                )
        );
    }

        @PostMapping("/register/participante")
        public ResponseEntity<ParticipanteDto> registerParticipante(
                @RequestBody ParticipanteDto participanteDto
        ) {
        ParticipanteDto saved = participanteService.createParticipantePublico(participanteDto);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
        }
}
