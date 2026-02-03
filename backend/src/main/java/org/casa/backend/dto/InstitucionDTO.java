package org.casa.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InstitucionDTO {

    private Long id;
    private String nombre;
    private String logoUrl;
    private boolean activo;
}
