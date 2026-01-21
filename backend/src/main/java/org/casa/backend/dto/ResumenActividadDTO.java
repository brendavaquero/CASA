package org.casa.backend.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResumenActividadDTO {

    private Long totalActividades;
    private Long totalPersonas;

    private List<ConteoDTO> porSexo;
    private List<ConteoDTO> porRangoEdad;
    private List<ConteoDTO> porPais;
    private List<ConteoDTO> porEstado;
}
