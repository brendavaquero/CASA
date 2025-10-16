package org.casa.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.casa.backend.entity.Usuario;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProgramaDto {
    private String idPrograma;
    private String nombre;
    private String descripcion;
    private List<String> responsablesIds;
}
