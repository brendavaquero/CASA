package org.casa.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResumenGeneralDto {

    private int totalActividades;
    private int totalTalleres;
    private int totalConvocatorias;

    private int totalPersonas;
    private int totalAlumnos;
    private int totalParticipantes;

    private int actividadesInfantiles;
    private int personasEnActividadesInfantiles;
}
