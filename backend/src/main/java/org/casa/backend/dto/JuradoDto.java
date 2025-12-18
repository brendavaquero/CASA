package org.casa.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JuradoDto {
    private String idJurado;
    private String idUsuario;
    private String idConvocatoria;
}
