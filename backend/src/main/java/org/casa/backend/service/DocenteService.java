package org.casa.backend.service;

import java.util.List;

import org.casa.backend.dto.DocenteDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface DocenteService {
    DocenteDto createDocente(DocenteDto docenteDto);
    DocenteDto getDocenteById(String idDocente);
    List<DocenteDto> getAllDocentes();
    DocenteDto updateDocente(String idUsuario, DocenteDto docenteDto, MultipartFile imagen);
    void deleteDocente(String idDocente);

}
