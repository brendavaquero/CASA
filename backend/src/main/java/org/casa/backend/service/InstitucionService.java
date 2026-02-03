package org.casa.backend.service;

import java.util.List;

import org.casa.backend.dto.InstitucionDTO;
import org.springframework.web.multipart.MultipartFile;

public interface InstitucionService {

    InstitucionDTO createInstitucion(InstitucionDTO dto, MultipartFile logo);

    InstitucionDTO getInstitucionById(Long id);

    List<InstitucionDTO> getAllInstituciones();

    InstitucionDTO updateInstitucion(Long id, InstitucionDTO dto, MultipartFile logo);

    void deleteInstitucion(Long id);
}