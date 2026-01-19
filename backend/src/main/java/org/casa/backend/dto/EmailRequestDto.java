package org.casa.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailRequestDto {
    private String correo;
    private String asunto;
    private String mensaje;
}
