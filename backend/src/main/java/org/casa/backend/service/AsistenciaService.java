package org.casa.backend.service;

import java.util.List;
import org.casa.backend.dto.AsistenciaDto;
import org.casa.backend.dto.AsistenciaAlumnoDto;

public interface AsistenciaService {
    AsistenciaDto registrarAsistencia(AsistenciaDto dto);
    AsistenciaDto obtenerPorId(String idAsistencia);
    List<AsistenciaDto> listarAsistencias();
    void eliminarAsistencia(String idAsistencia);
    List<AsistenciaDto> obtenerAsistenciasPorAlumno(String idAlumno);
    AsistenciaDto updateAsistencia(String asistenciaId, AsistenciaDto updateAsistencia);
    List<AsistenciaAlumnoDto> obtenerAsistencias(String idActividad);
}
