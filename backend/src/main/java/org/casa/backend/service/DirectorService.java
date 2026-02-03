package org.casa.backend.service;

import java.time.LocalDate;
import java.util.List;

import org.casa.backend.dto.DirectorDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface DirectorService {

    DirectorDto crear(
            String nombre,
            MultipartFile firma,
            Boolean activo,
            LocalDate fechaInicio,
            LocalDate fechaFin
    );

    DirectorDto actualizar(
            Long id,
            String nombre,
            MultipartFile firma,
            Boolean activo,
            LocalDate fechaInicio,
            LocalDate fechaFin
    );

    List<DirectorDto> listar();

    DirectorDto obtenerActivo();

    void eliminar(Long id);
}