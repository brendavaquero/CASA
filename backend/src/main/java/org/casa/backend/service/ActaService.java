package org.casa.backend.service;

import org.casa.backend.dto.ActaGanadorDto;

public interface ActaService {
    ActaGanadorDto generarActaPorConvocatoria(String idConvocatoria);

}
