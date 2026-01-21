package org.casa.backend.dto.distribucion;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.casa.backend.enums.Estado;
import org.casa.backend.enums.Pais;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DistribucionGeograficaDTO {

    private Map<Pais, Long> porPais;
    private Map<Estado, Long> porEstado;
}
