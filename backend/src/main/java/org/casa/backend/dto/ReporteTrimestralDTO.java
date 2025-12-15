package org.casa.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.casa.backend.enums.LenguaInd;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReporteTrimestralDTO {

    private int totalTalleres;
    private int totalParticipantes;

    private double porcentajeMujeres;
    private double porcentajeHombres;

    //private Long totalHombres;
    //private Long totalMujeres;
    private List<LenguaInd> lenguasRepresentadas;
    private List<EstadoParticipacionDTO> estadosConMayorParticipacion;

}
