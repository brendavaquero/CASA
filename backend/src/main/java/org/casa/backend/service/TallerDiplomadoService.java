package org.casa.backend.service;

import org.casa.backend.dto.ActividadDto;
import org.casa.backend.dto.TallerDiplomadoDto;
import org.casa.backend.enums.EstadoActividad;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;

public interface TallerDiplomadoService {
    TallerDiplomadoDto createTallerDiplomado(TallerDiplomadoDto tallerDiplomadoDto);
    TallerDiplomadoDto getTallerDiplomadoById(String idActividad);
    List<TallerDiplomadoDto> getAllTalleresDiplomados();
    TallerDiplomadoDto updateActividad(String actividadId, TallerDiplomadoDto updatedActividad);
    TallerDiplomadoDto updateTallerDiplo(String tallerId, TallerDiplomadoDto updatedTD);
    List<TallerDiplomadoDto> getTalleresByDocente(String idUsuario);
    //<PostulacionDto> obtenerPostulacionesPendientes(String idActividad);

    ActividadDto updateEstadoAct(String idActividad, EstadoActividad estado);
    String uploadImagenActividad(MultipartFile file, String idActividad);

}
