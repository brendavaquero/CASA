package org.casa.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ObraDto {
    private String idObra;
    private String idGanador;
    private String idArchivo;
    private String comentarios;
    private boolean versionActual;
}
