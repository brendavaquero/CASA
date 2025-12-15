package org.casa.backend.service.impl;

import lombok.AllArgsConstructor;
import org.casa.backend.dto.EstadoParticipacionDTO;
import org.casa.backend.dto.ReporteTrimestralDTO;
import org.casa.backend.entity.Alumno;
import org.casa.backend.enums.Estado;
import org.casa.backend.enums.LenguaInd;
import org.casa.backend.repository.AlumnoRepository;
import org.casa.backend.repository.TallerDiplomadoRepository;
import org.casa.backend.service.ReporteTrimestralService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor

public class ReporteTrimestralImpl implements ReporteTrimestralService {
    private final TallerDiplomadoRepository tallerRepository;
    private final AlumnoRepository alumnoRepository;

    @Override
    public ReporteTrimestralDTO generarReporte(int year, int trimestre) {

        LocalDate inicio;
        LocalDate fin;

        switch (trimestre) {
            case 1: inicio = LocalDate.of(year, 1, 1);  fin = LocalDate.of(year, 3, 31);  break;
            case 2: inicio = LocalDate.of(year, 4, 1);  fin = LocalDate.of(year, 6, 30);  break;
            case 3: inicio = LocalDate.of(year, 7, 1);  fin = LocalDate.of(year, 9, 30);  break;
            case 4: inicio = LocalDate.of(year, 10, 1); fin = LocalDate.of(year, 12, 31); break;
            default: throw new IllegalArgumentException("Trimestre inválido");
        }

        // talleres
        int totalTalleres = tallerRepository
                .findTalleresByTrimestre(inicio, fin)
                .size();

        // alumnos
        List<Alumno> alumnos = alumnoRepository.findAlumnosByTrimestre(inicio, fin);
        int totalParticipantes = alumnos.size();

        // sexo
        List<Object[]> sexos = alumnoRepository.countBySexo(inicio, fin);

        double hombres = 0;
        double mujeres = 0;

        for (Object[] row : sexos) {
            String sexo = (String) row[0];
            Long count = (Long) row[1];
            if (sexo.equalsIgnoreCase("H")) hombres = count;
            if (sexo.equalsIgnoreCase("M")) mujeres = count;
        }

        double porcentajeH = totalParticipantes == 0 ? 0 : (hombres / totalParticipantes) * 100;
        double porcentajeM = totalParticipantes == 0 ? 0 : (mujeres / totalParticipantes) * 100;

        // lenguas
        List<LenguaInd> lenguas = alumnoRepository.findLenguasRepresentadas(inicio, fin);

        // estados
        List<Object[]> estadosRaw = alumnoRepository.topEstados(inicio, fin);
        List<EstadoParticipacionDTO> estados = estadosRaw.stream()
                .map(o -> new EstadoParticipacionDTO((Estado) o[0], (Long) o[1]))
                .limit(5)
                .toList();

        return new ReporteTrimestralDTO(
                totalTalleres,
                totalParticipantes,
                porcentajeM,
                porcentajeH,
                lenguas,
                estados
        );
    }
}
