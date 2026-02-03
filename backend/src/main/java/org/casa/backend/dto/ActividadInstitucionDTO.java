package org.casa.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ActividadInstitucionDTO {
    private Long idInstitucion;
    private String nombre;
    private String logoUrl;
    private Integer orden;
    private boolean principal;
}

