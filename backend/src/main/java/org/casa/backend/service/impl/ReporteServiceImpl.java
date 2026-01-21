package org.casa.backend.service.impl;

import lombok.*;

import org.casa.backend.dto.ConteoDTO;
import org.casa.backend.dto.ReporteTrimestralDTO;
import org.casa.backend.dto.ResumenActividadDTO;
import org.casa.backend.entity.Alumno;
import org.casa.backend.entity.Participante;
import org.casa.backend.entity.Postulacion;
import org.casa.backend.repository.AlumnoRepository;
import org.casa.backend.repository.ConvocatoriaResidenciaRepository;
import org.casa.backend.repository.PostulacionRepository;
import org.casa.backend.repository.TallerDiplomadoRepository;
import org.casa.backend.service.ReporteTrimestralService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Service
@AllArgsConstructor
public class ReporteServiceImpl implements ReporteTrimestralService {

    private final TallerDiplomadoRepository tallerRepo;
    private final AlumnoRepository alumnoRepo;
    private final ConvocatoriaResidenciaRepository convocatoriaRepo;
    private final PostulacionRepository postulacionRepo;

    @Override
    public ReporteTrimestralDTO generarReporteTrimestral(int anio, int trimestre) {

        LocalDate inicio = obtenerInicioTrimestre(anio, trimestre);
        LocalDate fin = obtenerFinTrimestre(anio, trimestre);

        // === TALLERES ===
        Long totalTalleres = tallerRepo.contarTalleresEnPeriodo(inicio, fin);
        List<Alumno> alumnos = alumnoRepo.alumnosEnPeriodo(inicio, fin);

        ResumenActividadDTO resumenTalleres =
                construirResumen(totalTalleres, alumnos.stream()
                        .map(a -> a.getPostulacion().getParticipante())
                        .toList());

        // === CONVOCATORIAS ===
        Long totalConvocatorias = convocatoriaRepo.contarConvocatoriasEnPeriodo(inicio, fin);
        List<Postulacion> postulaciones =
                postulacionRepo.postulacionesConvocatoriaEnPeriodo(inicio, fin);

        ResumenActividadDTO resumenConvocatorias =
                construirResumen(totalConvocatorias, postulaciones.stream()
                        .map(Postulacion::getParticipante)
                        .toList());

        return new ReporteTrimestralDTO(
                anio,
                trimestre,
                resumenTalleres,
                resumenConvocatorias
        );
    }

    // =========================
    private ResumenActividadDTO construirResumen(
            Long totalActividades,
            List<Participante> personas
    ) {

        ResumenActividadDTO dto = new ResumenActividadDTO();
        dto.setTotalActividades(totalActividades);
        dto.setTotalPersonas((long) personas.size());

        dto.setPorSexo(contarPorSexo(personas));
        dto.setPorPais(contarPorPais(personas));
        dto.setPorEstado(contarPorEstado(personas));
        dto.setPorRangoEdad(contarPorEdad(personas));

        return dto;
    }

    private List<ConteoDTO> contarPorSexo(List<Participante> personas) {
        Map<String, Long> conteo = new HashMap<>();

        for (Participante p : personas) {
            String sexo = String.valueOf(p.getSexo());
            conteo.put(sexo, conteo.getOrDefault(sexo, 0L) + 1);
        }

        return conteo.entrySet().stream()
                .map(e -> new ConteoDTO(e.getKey(), e.getValue()))
                .toList();
    }

    private List<ConteoDTO> contarPorEdad(List<Participante> personas) {
        Map<String, Long> conteo = new HashMap<>();

        for (Participante p : personas) {
            int edad = Period.between(p.getFechaNacimiento(), LocalDate.now()).getYears();

            String rango =
                    edad < 18 ? "Menores de 18" :
                            edad <= 25 ? "18 a 25" :
                                    edad <= 40 ? "26 a 40" :
                                            "40+";

            conteo.put(rango, conteo.getOrDefault(rango, 0L) + 1);
        }

        return conteo.entrySet().stream()
                .map(e -> new ConteoDTO(e.getKey(), e.getValue()))
                .toList();
    }

    private LocalDate obtenerInicioTrimestre(int anio, int trimestre) {
        return switch (trimestre) {
            case 1 -> LocalDate.of(anio, 1, 1);   // Enero - Marzo
            case 2 -> LocalDate.of(anio, 4, 1);   // Abril - Junio
            case 3 -> LocalDate.of(anio, 7, 1);   // Julio - Septiembre
            case 4 -> LocalDate.of(anio, 10, 1);  // Octubre - Diciembre
            default -> throw new IllegalArgumentException(
                    "Trimestre inválido: " + trimestre
            );
        };
    }

    private LocalDate obtenerFinTrimestre(int anio, int trimestre) {
        return switch (trimestre) {
            case 1 -> LocalDate.of(anio, 3, 31);
            case 2 -> LocalDate.of(anio, 6, 30);
            case 3 -> LocalDate.of(anio, 9, 30);
            case 4 -> LocalDate.of(anio, 12, 31);
            default -> throw new IllegalArgumentException(
                    "Trimestre inválido: " + trimestre
            );
        };
    }

    private List<ConteoDTO> contarPorPais(List<Participante> personas) {
        Map<String, Long> conteo = new HashMap<>();

        for (Participante p : personas) {
            if (p.getPais() != null) {
                String pais = p.getPais().name();
                conteo.put(pais, conteo.getOrDefault(pais, 0L) + 1);
            }
        }

        return conteo.entrySet().stream()
                .map(e -> new ConteoDTO(e.getKey(), e.getValue()))
                .toList();
    }

    private List<ConteoDTO> contarPorEstado(List<Participante> personas) {
        Map<String, Long> conteo = new HashMap<>();

        for (Participante p : personas) {
            if (p.getEstado() != null) {
                String estado = p.getEstado().name();
                conteo.put(estado, conteo.getOrDefault(estado, 0L) + 1);
            }
        }

        return conteo.entrySet().stream()
                .map(e -> new ConteoDTO(e.getKey(), e.getValue()))
                .toList();
    }


}

