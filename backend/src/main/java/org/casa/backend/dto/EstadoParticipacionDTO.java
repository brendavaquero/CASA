package org.casa.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.casa.backend.enums.Estado;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EstadoParticipacionDTO {
    private Estado estado;
    private Long participantes;
}

