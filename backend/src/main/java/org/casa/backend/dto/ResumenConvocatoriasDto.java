package org.casa.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.casa.backend.dto.distribucion.DistribucionEdadDTO;
import org.casa.backend.dto.distribucion.DistribucionGeograficaDTO;
import org.casa.backend.dto.distribucion.DistribucionSexoDTO;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResumenConvocatoriasDto {

    private int totalConvocatorias;
    private int totalParticipantes;
    private double promedioParticipantesPorConvocatoria;

    private DistribucionSexoDTO distribucionSexo;
    private DistribucionEdadDTO distribucionEdad;
    private DistribucionGeograficaDTO distribucionGeografica;
}
