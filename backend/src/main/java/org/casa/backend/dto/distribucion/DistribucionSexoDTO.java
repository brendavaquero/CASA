package org.casa.backend.dto.distribucion;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DistribucionSexoDTO {

    private long hombres;
    private long mujeres;
    private long noEspecificado;
}
